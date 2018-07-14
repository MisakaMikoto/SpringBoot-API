app.controller('detailController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {

    if($cookies.get('memberId') === undefined) {
        disconnect();

    } else {
        var bookmark = new Bookmarks();

        let bookmarkPromise = bookmark.getListToIsbn($cookies.get('memberId'), $scope.detail.isbn);
        bookmarkPromise.then((response) => {
            if(response.length > 0) {
                if (response[0].isbn == $scope.detail.isbn) {
                    $('#bookmarking').attr('class', 'btn btn-primary disabled');
                    $('#bookmarking').html("이 책은 이미 북마킹 되었습니다.");
                    angular.element($('#bookmarking')).unbind('click');
                }
            }
        }, (error) => {
            console.error('Failed!', error);
        });

        $scope.bookmarking = () => {
            if (confirm("이 책을 북마킹 하겠습니까?") == true) {
                let bookmarkPromise = bookmark.add($cookies.get('memberId'));
                bookmarkPromise.then((response) => {
                    if (response.title) {
                        alert(response.title + " 책이 북마킹에 추가되었습니다.");

                    } else {
                        alert("북마킹에 실패하였습니다.");
                    }
                }, (error) => {
                    console.error('Failed!', error);
                });
            };
        };
    }

    function disconnect() {
        if($cookies.get('memberId') !== undefined && $cookies.get('memberName') !== undefined) {
            $cookies.remove('memberId');
            $cookies.remove('memberName');
        }
        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
        $location.path('/');
    }
}]);