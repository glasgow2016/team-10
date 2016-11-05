var app = require('express')();
var http = require('http').Server(app);
var database = require('./database.js');

app.get('/', function(req, res) {
	res.sendFile(__dirname + '/index.html');
});

console.log("I AM HERE");
console.log();

	/*
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
	*/
app.get('/group_create', function(req, res) {
	//add group into database
	var journey = [];
	var supervisor = "";
	var start_time = 0;
	var end_time = 0;
	var start_loc = 0;
	var end_loc = 0;
	//children can create a journey and add supervisor in it
	//a group can have no journey but it is advised to
	journey = database.journey.create(/*supervisor, start_loc, end_loc, start_time, end_time*/);
	
	if (journey.length() == 0){
		console.log("Please add a journey into your group");
	}
	
	database.group.create(/*name , journey*/);
	
});

app.get('/add_journey', function(req, res) {
	//add journey to a group
	var journey = [];
	journey = database.journey.create(/*supervisor, start, end*/);
	database.group.addJourney(journey);
	
});

app.get('/group_join', function(req, res) {
	//show who is in the group
	
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
		
	res.sendFile(__dirname + '/index.html');
});

app.get('/database', function(req, res) {
	res.sendFile(__dirname + '/database.js');
});

http.listen(3000, function() {
	console.log('listening on *:3000');
});