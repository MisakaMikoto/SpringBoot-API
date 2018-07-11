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
        let memberName = $cookies.get('memberName') + " 님 ";
        $('#memberName').html(memberName);
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

    function searchForKakao(pageIndex) {
        $scope.books = [];

        if(search.validSearch()) {
            let searchPromise = search.searchKakaoAPI($scope.query, pageIndex, $cookies.get('memberId'));
            searchPromise.then((response) => {
                if (response.books.length > 0) {
                    $('.table').show();
                    $scope.books = response.books;
                    $scope.pageList = search.pageList;
                    $scope.$apply();

                } else {
                    alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
                    logout();
                }

            }, (error) => {
                console.error('Failed!', error);
            });
        }
    }
}]);