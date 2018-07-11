app.controller('searchHistoryController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {

    var searchHistory = new SearchHistory();

    bindSelectChange();

    let searchHistoryPromise = searchHistory.getList($cookies.get('memberId'));
    searchHistoryPromise.then((response) => {
        if(response instanceof Array) {
            $scope.searchHistories = response;
            $scope.$apply();

        } else {
            alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
            logout();
        }
    }, (error) => {
        console.error('Failed!', error);
    });

    $scope.delete = () => {
        if (confirm("선택한 검색 히스토리를 삭제하시겠습니까?") == true) {
            let searchHistoryPromise = searchHistory.deleteList($cookies.get('memberId'));
            searchHistoryPromise.then((response) => {
                if(response instanceof Array) {
                    $scope.searchHistories = response;
                    $scope.$apply();

                } else {
                    alert('session disconnected. return login page');
                    logout();
                }

            }, (error) => {
                console.error('Failed!', error);
            });
        } else {
            return;
        }
    };

    $scope.sort = () => {
        alert(1);
    };

    $scope.back = () => {
        $location.path('/search');
    };

    function bindSelectChange() {
        $('#sort').on('change', (element) => {
            let sort = element.target.value;

            if('query' == sort) {
                let searchHistoryPromise = searchHistory.getListByQuery($cookies.get('memberId'));
                searchHistoryPromise.then((response) => {
                    if(response instanceof Array) {
                        $scope.searchHistories = response;
                        $scope.$apply();

                    } else {
                        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
                        logout();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else if('datetime' == sort) {
                let searchHistoryPromise = searchHistory.getListByDatetime($cookies.get('memberId'));
                searchHistoryPromise.then((response) => {
                    if(response instanceof Array) {
                        $scope.searchHistories = response;
                        $scope.$apply();

                    } else {
                        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
                        logout();
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });

            } else {
                // another case..
                ;
            }
        });
    }

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        $location.path('/');
        $scope.$apply();
    }
}]);