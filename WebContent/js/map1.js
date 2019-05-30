// *
// * Add multiple markers
// * 2013 - en.marnoto.com
// *

// necessary variables
var map;
var infoWindow;

// markersData variable stores the information necessary to each marker

var self = this;
var markersData1 = [];
var array = [];

function initialize() {
   var mapOptions = {
      center: new google.maps.LatLng(40.601203,-8.668173),
      zoom: 9,
      mapTypeId: 'roadmap',
   };

  
   
	var iconBase = 'http://localhost:8080/TravelRecommendation/images/';
    var icons = {
      parking: {
        icon: iconBase + 'aa.png'
      },
      library: {
        icon: iconBase + 'bb.jpg'
      },
      info: {
        icon: iconBase + 'cc.jpg'
      }
    };

    var features = [
      {
        position: new google.maps.LatLng(-33.91721, 151.22630),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91539, 151.22820),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91747, 151.22912),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91910, 151.22907),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91725, 151.23011),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91872, 151.23089),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91784, 151.23094),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91682, 151.23149),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91790, 151.23463),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91666, 151.23468),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.916988, 151.233640),
        type: 'info'
      }, {
        position: new google.maps.LatLng(-33.91662347903106, 151.22879464019775),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.916365282092855, 151.22937399734496),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.91665018901448, 151.2282474695587),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.919543720969806, 151.23112279762267),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.91608037421864, 151.23288232673644),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.91851096391805, 151.2344058214569),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.91818154739766, 151.2346203981781),
        type: 'parking'
      }, {
        position: new google.maps.LatLng(-33.91727341958453, 151.23348314155578),
        type: 'library'
      }
    ];

    // Create markers.
    features.forEach(function(feature) {
      var marker = new google.maps.Marker({
        position: feature.position,
        icon: icons[feature.type].icon,
        map: map
      });
    });
    var flightPath = new google.maps.Polyline({
          path: features,
          geodesic: true,
          strokeColor: '#008000',
          strokeOpacity: 1.0,
          strokeWeight: 2
        });
	
	
	
   
   
   map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
   $.get('getlocation',null, function(responseText) {
		responseText = responseText.replace("[","").replace("]","");
	
		self.array = responseText.split(",");
	
		for(i=0;i<self.array.length;i++){
			var dummyArray = self.array[i].split("#");
			var dummyObj = {lat:Number(dummyArray[0]), lng:Number(dummyArray[1]),name:(dummyArray[2]),address1:(dummyArray[3]) };
			markersData1.push(dummyObj);
		
		}
	

	
   });   
   
   
   
}
google.maps.event.addDomListener(window, 'load', initialize);



// This function will iterate over markersData array
// creating markers with createMarker function
