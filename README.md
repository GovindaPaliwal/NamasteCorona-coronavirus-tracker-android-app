# NamasteCorona - Coronavirus Tracker Android App

This is tracker and help information android application for the new coronavirus (2019-nCoV). It's uses basic API for tracking. For api you can check https://github.com/ExpDev07/coronavirus-tracker-api repository which is nicely develop and maintain by ExpDev07 (Specially thanks to contributor for this).
You can see country wise and province wise data with searching feature.
You can see all location point and data count on Map, and
Rendered information statistics with the help of charts.

Worldwide chart  |  country / province wise |  Map |  How To Stay Safe
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![image](https://github.com/GovindaPaliwal/NamasteCorona-coronavirus-tracker-android-app/blob/master/screens/first.png)  |  ![image](https://github.com/GovindaPaliwal/NamasteCorona-coronavirus-tracker-android-app/blob/master/screens/second.png) |  ![image](https://github.com/GovindaPaliwal/NamasteCorona-coronavirus-tracker-android-app/blob/master/screens/three.png) |  ![image](https://github.com/GovindaPaliwal/NamasteCorona-coronavirus-tracker-android-app/blob/master/screens/four.png)

### Live global stats (provided by [fight-covid19/bagdes](https://github.com/fight-covid19/bagdes)) from this API:**

![Covid-19 Confirmed](https://covid19-badges.herokuapp.com/confirmed/latest?long=true)
![Covid-19 Recovered](https://covid19-badges.herokuapp.com/recovered/latest?long=true)
![Covid-19 Deaths](https://covid19-badges.herokuapp.com/deaths/latest?long=true)

## Data

The data comes from the [2019 Novel Coronavirus (nCoV) Data Repository, provided
by JHU CCSE](https://github.com/CSSEGISandData/2019-nCoV) . It is
programmatically retrieved using API (Not store anywhere).

## Set Endpoints

You need to setup your server part which is written in python by ExpDev07
For api you can check [https://github.com/ExpDev07/coronavirus-tracker-api](https://github.com/ExpDev07/coronavirus-tracker-api) repository which is nicely develop and maintain by ExpDev07
And then set end point in APIClient.java file


For Map : You can add your api key for google map. used https://github.com/sharewire/google-maps-clustering lib for Google Maps Clustering

## Feature ✨
- [x] Country wise searching
- [x] View tracking using Map
- [x] Improve How To Stay Safe
- [x] Add Pia Chart for worldwide records
- [ ] Day by Day History

## Libraries
* [API By ExpDev07](https://github.com/ExpDev07/coronavirus-tracker-api): For Server Api.
* [Pia Charts](https://github.com/PhilJay/MPAndroidChart): A powerful 🚀 Android chart view / graph view library.
* [Google Maps Clustering for Android](https://github.com/sharewire/google-maps-clustering)

## Contributors

Please welcome if anyone want to contribute with above feature on other different feature.
Also please welcome to add How to stay safe information in besafe.html file which is available in asset folder.

## License

The application is available to the public strictly for educational and research purposes only.
