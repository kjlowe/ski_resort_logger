<?

$mysqli = new mysqli('localhost', 'diuus', '11pinecrest11', 'diuus_lifts');
$mysqli->query("SET SESSION time_zone ='-8:00';"); 


// Get the current lifts in the database. These are lifts that have been 
// open at some point today.
$query = "SELECT name, opened, closed FROM status WHERE date = CURDATE();";
$result = $mysqli->query($query);
if (!$result)
{
	echo "oh no";
	exit;
}

while ($row = $result->fetch_assoc()) {
    $current[$row["name"]]["opened"] = $row["opened"];
    $current[$row["name"]]["closed"] = $row["closed"];	
}

// Get the contents of the whistler blackcomb page
$html = file_get_contents("http://movement.whistlerblackcomb.com/wbwidget2012/liftstatus.php");

// Get lift names 
preg_match_all("/<td>([a-zA-Z0-9- ']+)<\/td>/", $html, $matches);
$lifts = $matches[1];

// Get lift statuses
preg_match_all("/<td class='([A-Z]+)'/", $html, $matches2);
$statuses = $matches2[1];


// Update the database when a chair opens or closes.
for($i = 0; $i < sizeof($lifts); ++$i)
{
  // The lift IS NOT in the database for this date and it now open.
  // INSERT a row and record the opening time.
  if (!isset($current[$lifts[$i]]) && $statuses[$i] == "OPEN")
  {
    $query .= "INSERT INTO `status` (`name`, `opened`, `date`) VALUES ('".addslashes($lifts[$i])."', CURTIME(), CURDATE());\n";
    $query .= "INSERT INTO `log` (`logtext`) VALUES ('".addslashes($lifts[$i])." is NOT in database. Inserting. Now Open.');\n";
  }
  // The lift IS in the database for this date, has not been recorded as open, but is now open.
  // UPDATE the entry with the opening time.
  else if (isset($current[$lifts[$i]]) && $current[$lifts[$i]]["opened"] == null && $statuses[$i] == "OPEN")
  {
    $query .= "UPDATE `status` SET `opened` = CURTIME(), `closed` = null WHERE name = '".addslashes($lifts[$i])."' AND `date` = CURDATE();\n";
    $query .= "INSERT INTO `log` (`logtext`) VALUES ('".addslashes($lifts[$i])." IS in database. Updating. Now Open.');\n";
  }

  // The lift IS NOT in the database for this date and it now on standby.
  // INSERT a row and record the standby time.
  else if (!isset($current[$lifts[$i]]) && $statuses[$i] == "STANDBY")
  {
    $query .= "INSERT INTO `status` (`name`, `standby`, `date`) VALUES ('".addslashes($lifts[$i])."', CURTIME(), CURDATE());\n";
    $query .= "INSERT INTO `log` (`logtext`) VALUES ('".addslashes($lifts[$i])." is NOT in database. Inserting. Now Standby.');\n";
  }

  // The lift IS in the database for this date, has not been recorded as closed, but is now closed.
  // UPDATE the entry with the closing time.
  else if (isset($current[$lifts[$i]]) && $current[$lifts[$i]]["closed"] == null && $statuses[$i] == "CLOSED")
  {
    $query .= "UPDATE `status` SET `closed` = CURTIME() WHERE name = '".addslashes($lifts[$i])."' AND `date` = CURDATE();\n";
    $query .= "INSERT INTO `log` (`logtext`) VALUES ('".addslashes($lifts[$i])." IS in database. Updating. No Closed.');\n";
  }
}
$result = $mysqli->multi_query($query);

if (!$result)
{
  die('Invalid query: ' . $mysqli->error());
}

$mysqli->close();

echo time();
?>