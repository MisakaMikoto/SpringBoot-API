app.controller("bookController", ['$scope', '$location', '$http', function ($scope, $location, $http) {
    $scope.search = function () {
        searchKakaoBook();
    };

    function searchKakaoBook() {
        let query = $scope.query;
        let commonPromise = new CommonPromise();
        commonPromise.get('/books?query=' + query).then((response) => {
            let responseJson = JSON.parse(response);
            clearGrid();
            createGrid(responseJson);

        }, function(error) {
            console.error("Failed!", error);
        });
    }

    function clearGrid() {
        $("#books").jqGrid('clearGridData');
    }

    function createGrid(responseJson) {
        $("#books").jqGrid({
            colModel: [
                { name: "title" },
                { name: "authors" },
                { name: "datetime" },
                { name: "publisher" },
                { name: "price" },
                { name: "salePrice" },
                { name: "saleYn" }
            ],
            data: responseJson
        });
    }
}]);