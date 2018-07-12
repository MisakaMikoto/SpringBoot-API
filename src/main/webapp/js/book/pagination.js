class Pagination {
    constructor() {
        this._lastPageNumber = '';
        this._pageCount = '';
    }

    set lastPageNumber(lastPageNumber) {
        this._lastPageNumber = Math.ceil(lastPageNumber);
    }

    set pageCount(pageCount) {
        this._pageCount = pageCount;
    }

    createPage(callback) {
        this.create(1, callback);
    }

    createNext(nextPageNumber, callback) {
        let span = '<span style="margin-right: 5px;"><u id="next" next="'+ nextPageNumber + '" style="cursor: pointer"> > </u></span>';
        $('#pagination').append(span);

        $('#next').bind('click', () => {
            this.goNext(callback);
        });
    }

    createPrev(prevPageNumber, callback) {
        let span = '<span style="margin-right: 5px;"><u id="prev" prev="'+ prevPageNumber + '" style="cursor: pointer"> < </u></span>';
        $('#pagination').prepend(span);

        $('#prev').bind('click', () => {
            this.goPrev(callback);
        });
    }

    create(startPageNumber, callback) {
        let pageCountWithStart = Number(startPageNumber) + Number(this._pageCount);

        if(Number(this._lastPageNumber) < pageCountWithStart) {
            pageCountWithStart = Number(this._lastPageNumber) + 1;
        }

        for(let i = startPageNumber; i < pageCountWithStart; i++) {
            let span = '<span class="pageSpan" style="margin-right: 5px;"><u id="'+ i + '" style="cursor: pointer">' + i + '</u></span>';
            $('#pagination').append(span);
        }

        if(startPageNumber > 1 && Number(this._lastPageNumber) < Number(pageCountWithStart)) {
            this.createPrev(Number(startPageNumber) - Number(this._pageCount), callback);
        }

        if(startPageNumber == 1 && Number(this._lastPageNumber) > Number(pageCountWithStart)) {
            this.createNext(Number(startPageNumber) + Number(this._pageCount), callback);
        }

        if(startPageNumber > 1 && Number(this._lastPageNumber) > Number(pageCountWithStart) - 1) {
            this.createPrev(Number(startPageNumber) - Number(this._pageCount), callback);
            this.createNext(Number(startPageNumber) + Number(this._pageCount), callback);
        }

        $('.pageSpan').on('click', (event) => {
            callback(event.target.getAttribute('id'));
        });
    }

    goNext(callback) {
        let pageNumber = Number($('#next').attr('next'));
        $('#pagination').empty();
        this.create(pageNumber, callback);
    }

    goPrev(callback) {
        let pageNumber = Number($('#prev').attr('prev'));
        $('#pagination').empty();
        this.create(pageNumber, callback);
    }
}