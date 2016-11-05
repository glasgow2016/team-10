var app = require('express')();
var http = require('http').Server(app);
var database = require('./database.js');

app.get('/', function(req, res) {
	res.sendFile(__dirname + '/index.html');
});

console.log("I AM HERE");
console.log();


database.createTables();


	/*
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
	*/
app.get('/group/create', function(req, res) {
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

app.get('/group/delete', function(req, res) {
	//delete group from database
	
	database.group.del(/*id? or group name*/);
	
});


app.get('/guardian/create', function(req, res) {
	//add journey to a group
	var name = "";
	var phone_num = "";
	
	database.guardian.create(/*name, phone_num*/);
	
});

app.get('/client/create', function(req, res) {
	//add journey to a group
	var name = "";
	var age = 0;
	var parent_phone = "";
	
	database.client.create(/*name, age, parent_phone*/);
	
});

app.get('/guardian/login', function(req, res) {
	//Welcome the parent
	
	//get the name and phone from login fields
	
	console.log("Welcome");
	console.log(database.client.getParent(/*name, phone_num*/));
	
	//display parent interface
	//TODO
});

app.get('/client/login', function(req, res) {
	//Welcome the child
	
	//get the name and parent phone from login fields
	
	console.log("Welcome");
	console.log(database.client.getChild(/*name, parent_phone_num*/));
	
	//display child interface
	//TODO
	
});

app.get('/journey/create', function(req, res) {
	//add journey to a group
	var journey = [];
	var supervisor = "";
	var start_time = 0;
	var end_time = 0;
	var start_loc = 0;
	var end_loc = 0;
	journey = database.journey.create(/*supervisor, start_loc, end_loc, start_time, end_time*/);
	database.group.addJourney(journey);
	
});

app.get('/group/display', function(req, res) {
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