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
	//creating client
	console.log('CREATE');
	var fname = req.query.fname;
	var sname = req.query.sname;
	var age = req.query.age;
	var phone_num = req.query.phone_num;

	database.client.create(fname, sname, age, phone_num);
	
});

app.get('/client/login/p', function(req, res) {
	//Welcome the child
	//get the id of tthe client
	var id = req.query.tagId;
	
	console.log("Welcome");
	database.client.login(id);
	
	//display child interface
	//TODO?
	
});

app.get('/guardian/create/p', function(req, res) {
	//create guardian
	
	var fname = req.query.fname;
	var sname = req.query.sname;
	var phone_num = req.query.phone_num;

	database.guardian.create(fname, sname, phone_num);
	
});

app.get('/guardian/auth/p', function(req, res) {
	
	var id = req.query.tagId;
	
	database.guardian.auth(id);
	
});

app.get('/guardian/get/p', function(req, res) {
	
	var phone_num = req.query.phone_num;
	
	var guardian_info = database.guardian.get(phone_num);
	
	return guardian_info;
	
});

app.get('/guardian/login', function(req, res) {
	//Welcome the parent
	
	console.log("Welcome");
	var phone_num = req.query.phone_num;
	
	database.guardian.login(phone_num);	
	
	//display parent interface
	//TODO?
	
});

app.get('/fellowship/delete/p', function(req, res) {
	//delete fellowship from database
	
	var id = req.query.tagId;
	
	database.fellowship.delete(id);
	
});

app.get('/fellowship/create/p', function(req, res) {
	//create fellowship
	var fname = req.query.fname;
	var sname = req.query.sname;
	var phone_num = req.query.phone_num;

	database.fellowship.create(fname, sname, phone_num);
	//return id;
});

app.get('/fellowship/addJourney/p', function(req, res) {
	
	//add journey
	var journey_id = req.query.journey_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.addJourney(journey_id, fellow_id);

});

app.get('/fellowship/join/p', function(req, res) {
	
	//join fellowship
	var client_id = req.query.client_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.join(client_id, fellow_id);
	//return success bool;

});

app.get('/fellowship/deleteJourney/p', function(req, res) {
	
	//delete fellowship
	
	var journey_id = req.query.journey_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.deleteJourney(journey_id, fellow_id);

});

app.get('/fellowship/get', function(req, res) {
	
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.get(fellow_id);
	
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