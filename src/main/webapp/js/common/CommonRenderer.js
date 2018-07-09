class CommonRenderer {

    clearGrid() {
        $("#books").jqGrid('clearGridData');
    }

    createBookGrid(responseJson) {
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

    createHistoryGrid(responseJson) {
        $("#histories").jqGrid({
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
}