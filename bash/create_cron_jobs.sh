#!/bin/bash
# create_cron_jobs.sh

# Write out current crontab

crontab -l > mycron

# Echo new cron into cron file
echo "*/1 * * * * php -f cron_jobs/lifts_parse.php" >> mycron
echo "0,30 * * * * php -f cron_jobs/weather_parse.php" >> mycron
echo "0.30 * * * * php -f cron_jobs/snow_report_parse.php" >> mycron

# Install new cron file
crontab mycron
rm mycron

#