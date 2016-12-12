<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<html> 
	<head> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" > 
		<title>lift history</title> 
		<style type="text/css"> 
			body
			{
				background-color: #000000;
				color: #dddddd;
				font-family: Tahoma, Geneva, sans-serif;
			}
			a
			{
				font-size: 15px;
				text-decoration: none;
				font-weight: bold;
				color: #dddddd;
			}
 

		</style>
		
		<script type="text/javascript">

			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-30476137-1']);
			_gaq.push(['_trackPageview']);

			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();

		</script>

</head>
<html>
<body>


<?

date_default_timezone_set("America/Vancouver");
$days_to_show = 7;

// Connect to SQL 
$mysqli = new mysqli('localhost', 'diuus', '11pinecrest11', 'diuus_lifts');

// If there's an error quit.
if ($mysqli->connect_errno) {
    printf("Connect failed: %s\n", $mysqli->connect_error);
    exit();
}

$mysqli->query("SET time_zone= '-8:00';") OR die("ERROR");  

if (isset($_GET["debug"]))
{
	if ($result = $mysqli->query("SELECT CURTIME() as time;", MYSQLI_USE_RESULT) OR die($mysqli->error))
	{
		if ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
			echo $row['time'];
		}
		$result->close();
	}
}
	
// Get an arary of dates for the past past 7 days
$dates = array();
$query = "SELECT ";
for ($i = 0; $i < $days_to_show; ++$i)
{
    if ($i != 0) $query .= " ,";
    $query .= "DATE_SUB(CURDATE(), INTERVAL ".$i." DAY) AS date".$i;
}
if ($result = $mysqli->query($query, MYSQLI_USE_RESULT) OR die($mysqli->error))
{
	if ($row = $result->fetch_array(MYSQLI_ASSOC))
	{
		for ($i = 0; isset($row["date".$i]); ++$i)
		{
			$dates[$i] = $row["date".$i];
		}
	}
	$result->close();
}

// Get an array of opening times for the past week
$history = array();
for ($i = 0; $i < sizeof($dates); ++$i)
{
    $query = "SELECT name, TIME_FORMAT(opened, '%l:%i%p') as opened, TIME_FORMAT(closed, '%l:%i%p') as closed, TIME_FORMAT(standby, '%l:%i%p') as standby FROM status WHERE date = '".$dates[$i]."';";
	if ($result = $mysqli->query($query, MYSQLI_USE_RESULT) OR die($mysqli->error))
	{
		while ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
		  $history[$row["name"]][$i]["opened"] = $row["opened"];
		  $history[$row["name"]][$i]["closed"] = $row["closed"];
		  $history[$row["name"]][$i]["standby"] = $row["standby"];
		}
		$result->close();
	}

}

echo "<div style='margin: 50px auto 0px auto; width: 800px; text-align:center;'>";

if (isset($_GET["showall"]))
{
	echo "<a href='lifts.php'>Okay, just show the alpine lifts.</a>";	
}
else
{
	echo "<a href='lifts.php?showall'>Click to see the whole list of lifts.</a>";
}

echo "<table width='800px' style='margin: 30px 0 30px 0;'><tr><th width='100px'></th>";

for ($i = sizeof($dates) - 1; $i >= 0; --$i)
{
	$day = ($i == 0) ? "Today" : date("l", time() - $i * 86400);
	$day = ($i == 1) ? "Yesterday" : $day;
	echo "<th width='100px'>".$day."</th>\n";
}

echo "</tr>\n";
echo "<tr><td></td></tr>";
echo "<tr><td style='padding-top:5px; line-height: 10px;''>SNOWFALL<br /><span style='font-size: 8px;'>Overnight + Daytime</span></td>";
print_snowfall_row($mysqli, $days_to_show);
echo "</tr>";

echo "</tr>\n";
echo "<tr><td></td></tr>";
echo "<tr><td>HARMONY</td>";
print_chair_row("HARMONY 6 EXPRESS");
echo "</tr>";

echo "<tr><td>PEAK</td>";
print_chair_row("PEAK EXPRESS");
echo "</tr>";

echo "<tr><td>SYMPHONY</td>";
print_chair_row("SYMPHONY EXPRESS");
echo "</tr>";

echo "<tr><td>T-BARS</td>";
print_chair_row("T-BARS");
echo "</tr>";

if (isset($_GET["showall"]))
{
	echo "<tr><td>CREEKSIDE</td>";
	print_chair_row("CREEKSIDE GONDOLA");
	echo "</tr>";
	
	echo "<tr><td>BIG RED</td>";
	print_chair_row("BIG RED EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>VILLAGE GONDOLA</td>";
	print_chair_row("WHISTLER VILLAGE GONDOLA");
	echo "</tr>";

	echo "<tr><td>FITZSIMMONS</td>";
	print_chair_row("FITZSIMMONS EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>GARBANZO</td>";
	print_chair_row("GARBANZO EXPRESS");
	echo "</tr>";
		
	echo "<tr><td>EMERALD</td>";
	print_chair_row("EMERALD EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>FRANZ'S</td>";
	print_chair_row("FRANZ&#39;S CHAIR");
	echo "</tr>";
	
	echo "<tr><td>OLYMPIC</td>";
	print_chair_row("OLYMPIC CHAIR");
	echo "</tr>";

	echo "<tr><td></td></tr>";
	
	echo "<tr><td>PEAK2PEAK</td>";
	print_chair_row("PEAK 2 PEAK GONDOLA");
	echo "</tr>";
}

echo "<tr><td></td></tr>";

echo "<tr><td>GLACIER</td>";
print_chair_row("GLACIER EXPRESS");
echo "</tr>";

echo "<tr><td>SHOWCASE</td>";
print_chair_row("SHOWCASE T-BAR");
echo "</tr>";

echo "<tr><td>HORSTMAN</td>";
print_chair_row("HORSTMAN T-BAR");
echo "</tr>";

echo "<tr><td>7TH HEAVEN</td>";
print_chair_row("7TH HEAVEN EXPRESS");
echo "</tr>";

echo "<tr><td>CRYSTAL</td>";
print_chair_row("CRYSTAL RIDGE EXPRESS");
echo "</tr>";

if (isset($_GET["showall"]))
{
	echo "<tr><td>EXCALIBUR</td>";
	print_chair_row("EXCALIBUR GONDOLA");
	echo "</tr>";
	
	echo "<tr><td>EXCELERATOR</td>";
	print_chair_row("EXCELERATOR EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>JERSEY CREAM</td>";
	print_chair_row("JERSEY CREAM EXPRESS");
	echo "</tr>";

	echo "<tr><td>WIZARD</td>";
	print_chair_row("WIZARD EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>SOLAR COASTER</td>";
	print_chair_row("SOLAR COASTER EXPRESS");
	echo "</tr>";
	
	echo "<tr><td>CAT SKINNER</td>";
	print_chair_row("CATSKINNER CHAIR");
	echo "</tr>";
	
	echo "<tr><td>MAGIC</td>";
	print_chair_row("MAGIC CHAIR");
	echo "</tr>";

	echo "<tr><td></td></tr>";
	
	echo "<tr><td>TUBE PARK</td>";
	print_chair_row("COCA-COLA TUBE PARK") ;
	echo "</tr>";
}

echo "</table>";


if (isset($_GET["debug"]))
{
	print_r($history);
}


$mysqli->close();


function print_chair_row($chair_name)
{
	global $dates, $history;
	for ($i = sizeof($dates) - 1; $i >= 0; --$i)
	{
		$did_open = isset($history[$chair_name][$i]) && $history[$chair_name][$i]["opened"] != null;
		$did_close = isset($history[$chair_name][$i]) && $history[$chair_name][$i]["closed"] != null;
		$did_standby = isset($history[$chair_name][$i]) && $history[$chair_name][$i]["standby"] != null;
		
		if ($did_standby && $did_close && strtotime( $history[$chair_name][$i]["closed"] ) < strtotime( "8:30" ))
		{
		 		 $did_standby = false;
		}

		$color = $did_open ? "green" : ($did_standby ? "#F4CD3F" : "red");
		$text = $did_open ? $history[$chair_name][$i]["opened"] : "";
		$text = $did_open && $did_close ? $text . "<br /><span style='font-size: 8px;'>until " . $history[$chair_name][$i]["closed"] . "</span>" : $text;
		
		$text = !$did_open && $did_standby ? $history[$chair_name][$i]["standby"] : $text;
		$text = !$did_open && $did_standby && $did_close ? $text . "<br /><span style='font-size: 8px;'>until " . $history[$chair_name][$i]["closed"] . "</span>" : $text;
   
		$add_style = !$did_open && $did_standby ? " color: #333333;" : "";

		echo "<td bgcolor='".$color."' height='40px' style='font-size: 11px; text-align: center; line-height: 8px;".$add_style."'>".$text."</td>\n";
	}
}

function print_freezing_level_row($mysqli)
{
    $query = "SELECT COUNT(*) AS count FROM stations;";
	if ($result = $mysqli->query($query, MYSQLI_USE_RESULT) OR die($mysqli->error))
	{
		$row = $result->fetch_array(MYSQLI_ASSOC);
		echo "<td>" . $row["count"] . "</td>";
	}
}
 
function print_snowfall_row($mysqli, $days_to_show)
{
	$query = "SELECT
			calendar_table.dt,
			IF(data.morning_time < '12:00:00', data.morning_snow, null) AS morning_snow,
			IF(data.afternoon_time > '12:00:00', data.afternoon_snow, null) AS afternoon_snow

		FROM calendar_table

		LEFT JOIN 

		    (SELECT 
		            r3.`date` 			AS date,
		            TIME(r3.min_time) 	AS morning_time,
		            r1.`Last12Hours` 	AS morning_snow,
		            TIME(r3.max_time) 	AS afternoon_time,
		            r2.`Last12Hours`	AS afternoon_snow
		    
		    FROM 	snowreport r1,
		            snowreport r2,
		            (SELECT	DATE(`tomupdated`) 	AS date,
		             		MAX(`tomupdated`) 	AS max_time,
		             		MIN(`tomupdated`) 	AS min_time
		             FROM snowreport
		             WHERE `tomupdated` > DATE_SUB(CURDATE(), INTERVAL " . $days_to_show . " DAY)
		             GROUP BY date) r3
		    
		    WHERE	r1.`tomupdated` = r3.min_time AND
		            r2.`tomupdated` = r3.max_time) data

		ON data.date = calendar_table.dt

		WHERE 	calendar_table.dt > DATE_SUB(CURDATE(), INTERVAL " . $days_to_show . " DAY) AND
				calendar_table.dt <= CURDATE();";

	if ($result = $mysqli->query($query, MYSQLI_USE_RESULT) OR die($mysqli->error))
	{
		while ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
			$snow_fell = ($row["afternoon_snow"] > 0 || $row["morning_snow"] > 0);
			$text_color = $snow_fell ? "#333333" : "#dddddd";
			$cell_color = $snow_fell ? "dddddd" : "333333";
			$dayText = ($row["afternoon_snow"] != null) ? "<br /><span style='font-size: 8px;'>+ " . $row["afternoon_snow"] . "cm</span>" : "";

			if ($row["morning_snow"] != null)
			{
				echo "<td bgcolor='".$cell_color."' height='40px' style='font-size: 13px; text-align: center; line-height: 8px; color: ".$text_color.";'>" . $row["morning_snow"] . "cm" . $dayText . "</td>\n";		
			}
			else
			{
				echo "<td bgcolor='333333' height='40px' style='font-size: 13px; text-align: center; line-height: 8px;'></td>\n";						
			}
		}
		$result->close();
	}
}

?>

</div>

<style type='text/css'>@import url('http://getbarometer.s3.amazonaws.com/assets/barometer/css/barometer.css');</style>
<script src='http://getbarometer.s3.amazonaws.com/assets/barometer/javascripts/barometer.js' type='text/javascript'></script>
<script type="text/javascript" charset="utf-8">
  BAROMETER.load('nxZ5ny3MNSgQPBqn6WkRT');
</script>

</body>
</html>

