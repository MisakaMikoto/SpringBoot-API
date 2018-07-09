class SearchHistory {
    constructor(){};

    getList() {
        let commonPromise = new CommonPromise();
        return commonPromise.get('/histories').then((response) => {
            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }
}