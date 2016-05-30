'use strict';

// based on starter_kit_js_app-solutions
// most of below is a simple copy-paste with deep code analyzing
// as it was a standard solution for creating and populating  html table with data from external source

var gamesData = (function() {

    var surroundWithTableDataTag = function(data) {
            return '<td>' + data + '</td>';
        },
        surroundWithTableRowTag = function(data) {
            return '<tr>' + data + '</tr>';
        },
        handleResponse = function(arr) {
            var out = '';
            for (var i = 0; i < arr.length; i++) {
			out = out + surroundWithTableRowTag(surroundWithTableDataTag(arr[i].id) + surroundWithTableDataTag(arr[i].title) + surroundWithTableDataTag(arr[i].year) +
                                          surroundWithTableDataTag(arr[i].genre) + surroundWithTableDataTag(arr[i].publisher) + surroundWithTableDataTag(arr[i].platform) +
                                          surroundWithTableDataTag(arr[i].metascore));
            }

            // innerHTML - change the HTML content of an element with given id
            document.getElementById('gameTable').innerHTML = out;
        };

    return {
        getGames: function() {
            // XMLHttpRequest (XHR) is an API available to web browser scripting languages
            // It is used to send HTTP or HTTPS requests to a web server and load the server response data back into the script
            var xmlhttp = new XMLHttpRequest();
            var url = 'games.json'

            // onreadystatechange - stores a function (or the name of a function) to be called automatically each time the readyState property changes
            // readyState - holds the status of the XMLHttpRequest. Changes from 0 to 4:
            // 0: request not initialized, 1: server connection established, 2: request received, 3: processing request, 4: request finished and response is ready
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var myArr = JSON.parse(xmlhttp.responseText);
                    handleResponse(myArr);
                }
            };
            // open( Method, URL, Asynchronous, UserName, Password )
            xmlhttp.open('GET', url, true);
            xmlhttp.send();
        }
    };
})();
