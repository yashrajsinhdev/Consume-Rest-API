var app = angular.module("myPage", []);

app.controller("myController", [
	"$scope",
	"$http",
	function($scope, $http) {

		$scope.myCall = function() {
			
			var host = 'http://localhost:1234'; 
			$scope.url = host + '/myApi'
			$scope.method = 'GET';
			var myValue1 = 'yourValue';
			var your_data = { "myValue1": myValue1 };

			$http({
				method: $scope.method,
				url: $scope.url,
				headers: { 'Content-Type': 'application/json'},
				params: your_data
			}).then(function(response) {
				alert(JSON.stringify(response.data))
			}, function(error) {
				alert("We are in error")
			})

		}



	}])
