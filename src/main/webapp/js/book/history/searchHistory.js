class SearchHistory {
    constructor(){};

    getList(memberId) {
        let commonPromise = new CommonPromise();
        return commonPromise.get('/histories?memberId=' + memberId).then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }
}