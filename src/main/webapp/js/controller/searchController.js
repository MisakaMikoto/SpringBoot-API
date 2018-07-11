app.controller('searchController', ['$scope', '$location', '$cookies', '$uibModal', function ($scope, $location, $cookies, $uibModal) {

    var search = new Search();

    setUserName();

    $scope.search = () => {
        searchForKakao(1);
    };

    $scope.searchPage = (pageIndex) => {
        searchForKakao(pageIndex);
    };

    $scope.showDetail = (index) => {
        $scope.detail = search.showDetail(index);
        createModal();
    };

    $scope.goHistories = () => {
        $location.path('/history');
    };

    $scope.logout = () => {
        logout();
    };

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        $location.path('/');
        $scope.$apply();
    }

    function setUserName() {
        $('#memberName').html($cookies.get('memberName'));
    }

    function createModal() {
        $uibModal.open({
            templateUrl: '/component/detail.html',
            scope: $scope,
            controller: function ($scope, $uibModalInstance) {
                $scope.ok = function () {
                    $uibModalInstance.close();
                };
            }
        });
    }

    function validSearch() {
        if($('#query').val().length == "") {
            alert("input search query");
            return false;

        } else if($( "#size option:selected" ).val() == "0") {
            alert("select search size");
            return false;

        } else if($( "#sort option:selected" ).val() == "none") {
            alert("select search sort");
            return false;

        } else {
            return true;
        }
    }

    function searchForKakao(pageIndex) {
        $scope.books = [];

        if(validSearch()) {
            let searchPromise = search.searchKakaoAPI($scope.query, pageIndex, $cookies.get('memberId'));
            searchPromise.then((response) => {
                if (response.books.length > 0) {
                    console.log(response);

                    $scope.books = response.books;
                    $scope.pageList = search.pageList;
                    $scope.$apply();

                } else {
                    alert('session disconnected. return login page');
                    logout();
                }

            }, (error) => {
                console.error('Failed!', error);
            });
        }
    }
}]);