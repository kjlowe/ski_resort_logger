<?

$mysqli = new mysqli('localhost', 'diuus', '11pinecrest11', 'diuus_lifts');
$mysqli->query("SET SESSION time_zone ='-8:00';"); 


// Get the contents of the whistler blackcomb page
$json = file_get_contents("http://www.whistlerblackcomb.com/php/website/weather_tom.php");
$data = json_decode($json, true);


if (isset($data["Date"]["tomupdated"]))
{
	$query = "INSERT IGNORE INTO `snowreport`
	 	SET `tomupdated` = '" . date('Y-m-d G:i:s', strtotime($data["Date"]["tomupdated"])) . "',
		`Base` 			= " . intval($data["Snow"]["Base"]) . ",
		`Last12Hours` 	= " . intval($data["Snow"]["Last12Hours"]) . ",
		`Last24Hours` 	= " . intval($data["Snow"]["Last24Hours"]) . ",
		`Last48Hours` 	= " . intval($data["Snow"]["Last48Hours"]) . ",
		`Last7Days` 	= " . intval($data["Snow"]["Last7Days"]) . ",
		`CumulativeSnow` = " . intval($data["Snow"]["CumulativeSnow"]) . ",
		`Visibility` 	= '" . $data["Conditions"]["Visibility"]. "',
		`FreezingLevel` = '" . $data["Conditions"]["FreezingLevel"]. "',
		`CurrentConditionsValley` 	= '" . $data["Conditions"]["CurrentConditionsValley"]. "',
		`CurrentConditionsMid` 		= '" . $data["Conditions"]["CurrentConditionsMid"]. "',
		`CurrentConditionsAlpine` 	= '" . $data["Conditions"]["CurrentConditionsAlpine"]. "',
		`SurfaceConditionPrimary` 	= '" . $data["Conditions"]["SurfaceConditionPrimary"]. "',
		`SurfaceConditionSecondary` = '" . $data["Conditions"]["SurfaceConditionSecondary"]. "',
		`Snowmaking` 				= '" . $data["Conditions"]["Snowmaking"]. "',
		`TotalAcresOpen` 			= '" . $data["Conditions"]["TotalAcresOpen"]. "',
		`WhistlerPark` 				= '" . ($data["Park"]["Whistler"] == "Open") . "',
		`BlackcombPark` 			= '" . ($data["Park"]["Blackcomb"] == "Open") . "',
		`RunsGroomedWhistler` 		= " . intval($data["Grooming"]["RunsGroomedWhistler"]) . ",
		`RunsGroomedBlackcomb` 		= " . intval($data["Grooming"]["RunsGroomedBlackcomb"]) . ",
		`RunsOpenWhistler` 			= " . intval($data["Grooming"]["RunsOpenWhistler"]) . ",
		`RunsOpenBlackcomb` 		= " . intval($data["Grooming"]["RunsOpenBlackcomb"]) . ",
		`LiftsOpenWhistler` 		= " . intval($data["Grooming"]["LiftsOpenWhistler"]) . ",
		`LiftsOpenBlackcomb` 		= " . intval($data["Grooming"]["LiftsOpenBlackcomb"]) . ",
		`AcresGroomedWhistler` 		= " . intval($data["Grooming"]["AcresGroomedWhistler"]) . ",
		`AcresGroomedBlackcomb` 	= " . intval($data["Grooming"]["AcresGroomedBlackcomb"]) . ";";

	$result = $mysqli->multi_query($query);
	echo $query;
	if (!$result)
	{
	  die('Invalid query: ' . $mysqli->error);
	}
}

echo time();


$mysqli->close();
?>