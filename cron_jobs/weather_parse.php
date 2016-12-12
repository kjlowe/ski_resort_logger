<?

$mysqli = new mysqli('localhost', 'diuus', '11pinecrest11', 'diuus_lifts');
$mysqli->query("SET SESSION time_zone ='-8:00';"); 


// Get the contents of the whistler blackcomb page
$json = file_get_contents("http://www.whistlerblackcomb.com/php/website/weather_wxcurrent.php");
$data = json_decode($json, true);

$stations = $data["WeatherStation"];

/*
[StationName] => Creekside Village
[StationID] => 103
[CurrentDate] => 12/24/2015 6:15:00 PM
[Elevation] => 640
[MaxTemp] => -4.4
[AvgTemp] => -4.5
[MinTemp] => -4.6
[MaxWind] => 0.0
[AvgWind] => -49.8
[WindDir] => -90.0
[WindRun] => Array ( [0] => )
[RH] => 87.9
[BP] => 893.0
[BattVoltage] => 13.2900
[ModuleTemp] => 29.2700
[Ext1] => 0.0000 )

Whistler Village [StationID] = 160
Whistler Peak [StationID] = 201
Roundhouse [StationID] = 203
Horstman Hut [StationID] = 100
Pig Alley [StationID] = 204
Rendezvous Lodge [StationID] = 110
Crystal Chair [StationID] = 140
Catskinner [StationID] = 120
Harmony Ridge [StationID] = 109
Symphony [StationID] = 206
Creekside Village [StationID] = 103
*/


$query = "";
foreach ($stations AS $index => $station)
{

	$query .= "INSERT IGNORE INTO `stations`
	SET `StationID` = " . $station["StationID"] . ",
	`MaxTemp` = " . (is_array($station["MaxTemp"]) ? 0 : $station["MaxTemp"]) . ",
	`AvgTemp` = " . (is_array($station["AvgTemp"]) ? 0 : $station["AvgTemp"]) . ",
	`MinTemp` = " . (is_array($station["MinTemp"]) ? 0 : $station["MinTemp"]) . ",
	`MaxWind` = " . (is_array($station["MaxWind"]) ? 0 : $station["MaxWind"]) . ",
	`AvgWind` = " . (is_array($station["AvgWind"]) ? 0 : $station["AvgWind"]) . ",
	`WindDir` = " . (is_array($station["WindDir"]) ? 0 : $station["WindDir"]) . ",
	`RH` = " . (is_array($station["RH"]) ? 0 : $station["RH"]) . ",
	`BP` = " . (is_array($station["BP"]) ? 0 : $station["BP"]) . ",
	`Timestamp` = '" . date('Y-m-d G:i:s', strtotime($station["CurrentDate"])) . "'; ";
}

$result = $mysqli->multi_query($query);

if (!$result)
{
  die('Invalid query: ' . $mysqli->error);
}


echo $mysqli->error;

echo time();


$mysqli->close();
?>