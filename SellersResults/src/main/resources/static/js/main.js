//---------- Add product ---------- //
// $(document).ready(function () {
//     let max_fields = 100;
//     let index = 1;
//
//
//     $('.add-product').click(function () {
//
//         if (index < max_fields) {
//             let newRow = jQuery('<div class="col-auto"> ' +
//                 ' <label for="product">Product</label> ' +
//                 ' <select th:field="*{products}" id="product" class="custom-select" ' +
//                 ' <option value="">Select Product</option> ' +
//                 ' <option th:each="p : ${products}" ' +
//                 'th:value="${p.name}" ' +
//                 'th:text="|${p.category}: ${p.name} ${p.price}|"> ' +
//                 ' </option> ' +
//                 '  </select> ' +
//                 '</div>');
//             index++;
//
//             $('.products').append(newRow)
//         }
//     });
// });

//---------- Stores results ---------- //
$('#loadStores').click(() => {
    reloadResults()
});

function reloadResults() {
    $("#stores-container").empty();

    fetch("http://localhost:8080/stores-results/week").
    then(response => response.json()).
    then(json => json.forEach(store => {
        console.log(store)
        // let tableRow = '<tr>' +
        //     '<td>' + store.storeName + '</td>' +
        //     '<td>' + store.turnover + '</td>' +
        //     '<td>' + store.visitors + '</td>' +
        //     '<td>' + store.countOfSales + '</td>' +
        //     '<td>' + store.countOfProducts + '</td>' +
        //     '<td>' + store.percentageSales + '</td>' +
        //     '<td>' + store.averagePricePerBasket + '</td>' +
        //     '<td>' + store.averagePricePerProducts + '</td>' +
        //     '<td>' + store.upt + '</td>' +
        //     '</tr>'
        //
        // $("#stores-container").append(tableRow)
    }))
}