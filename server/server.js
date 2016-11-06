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
	

app.get('/client/create', function(req, res) {
	//add journey to a group
	//var id = req.query.tagId;
	
	var name = "";
	var age = 0;
	var parent_phone = "";
	
	database.client.create(/*name, age, parent_phone*/);	
});

app.get('/client/login', function(req, res) {
	//Welcome the child
	
	//get the name and parent phone from login fields
	
	console.log("Welcome");
	console.log(database.client.getChild(/*name, parent_phone_num*/));
	
	//display child interface
	//TODO
	
});

app.get('/guardian/create', function(req, res) {
	//add journey to a group
	var name = "";
	var phone_num = "";
	
	database.guardian.create(/*name, phone_num*/);
	
});

app.get('/guardian/auth', function(req, res) {
	
	database.guardian.auth(/*client id*/);
	
});

app.get('/guardian/get', function(req, res) {
	
	database.guardian.get(/*parent phone number*/);
	
	//return phone_num, fname, sname;
	
});

app.get('/guardian/login', function(req, res) {
	//Welcome the parent
	
	//get the name and phone from login fields
	
	console.log("Welcome");
	console.log(database.client.getParent(/*name, phone_num*/));
	
	//display parent interface
	//TODO
});

app.get('/fellowship/delete/p', function(req, res) {
	//delete group from database
	
	var id = req.query.tagId;
	var name = req.query.name;
	console.log(name);
	console.log(id);
	
	database.fellowship.del(/*id*/);
	
});

app.get('/fellowship/create', function(req, res) {
	//create fellowship
	database.fellowship.create(/*name, phone, callback?*/);
	//return id;
});

app.get('/fellowship/addJourney', function(req, res) {
	
	//add journey
	database.fellowship.addJourney(/*journeyID, fellowID*/);

});

app.get('/fellowship/join', function(req, res) {
	
	//join fellowship
	database.fellowship.join(/*clientID, fellowID*/);
	//return success bool;

});

app.get('/fellowship/deleteJourney', function(req, res) {
	
	//delete fellowship
	database.fellowship.deleteJourney(/*journeyID, fellowID*/);

});

app.get('/fellowship/get', function(req, res) {
	//show who is in the group
	
	database.fellowship.get(/*id, callback*/);
	
	/*
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
		
	res.sendFile(__dirname + '/index.html');
	*/
	
	//return name, child list, parents
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


app.get('/journey/start', function(req, res) {
	//start a journey
	
	database.journey.start(/*journey id, time*/);
		
});

app.get('/journey/end', function(req, res) {
	//end a journey
	
	database.journey.end(/*journey id, time*/);
		
});

app.get('/database', function(req, res) {
	res.sendFile(__dirname + '/database.js');
});

http.listen(3000, function() {
	console.log('listening on *:3000');
});