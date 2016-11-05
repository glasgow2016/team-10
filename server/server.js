var app = require('express')();
var http = require('http').Server(app);
var database = require('./database.js');

app.get('/', function(req, res) {
	res.sendFile(__dirname + '/index.html');
});
console.log("I AM HERE");
console.log(database.group.create());

app.get('/group_create', function(req, res) {
	//add group into database
});

app.get('/group_join', function(req, res) {
	//show who is in the group
});

app.get('/database', function(req, res) {
	res.sendFile(__dirname + '/database.js');
});

http.listen(3000, function() {
	console.log('listening on *:3000');
});