// $(document).ready(function () {
//     let max_fields = 100;
//     let index = 1;
//
//
//     $('.add-product').click(function () {
//
//         if (index < max_fields) {
//             let newRow = jQuery(' <select th:field="products[' + index + ']" ' +
//                 ' id="products" ' +
//                 ' class="custom-select"> ' +
//                 ' <option value="" th:text="#{selectProduct}">Select Product</option> ' +
//                 ' <option th:each="p : ${products}" ' +
//                 ' th:value="${p.name}" ' +
//                 ' th:text="|${p.category}: ${p.name} ${p.price}|"> ' +
//                 ' </option> ');
//
//             index++;
//
//             $("#myProducts").append(newRow)
//         }
//
//     });
// });


// ----------- Add Sale ---------- //
$(document).ready(function () {
    let index = 1;

    $("#addProduct").click(function () {
        let newRow = jQuery(' <div class="col-auto"> ' +
            ' <div class="d-flex justify-content-center mt-2"> ' +
            ' <input name="products[' + index + ']" list="products" id="product" class="form-control"/> ' +
            ' </div> ' +
            ' </div> ');

        index++;

        $('#myProducts').append(newRow)
    });
});
// $(document).ready(function() {
//
//     $("#addProduct").click(function() {
//         let selectedProduct = $('#products').val()
//         $("#myProducts").append(`<div class="d-flex justify-content-center mt-1"><input type="text" value="${selectedProduct}" name="newProduct" readonly/><a href="#" class="delete">Delete</a></div>`);
//     });
//
//     $("#myProducts").on("click", ".delete", function(e) {
//         e.preventDefault();
//         $(this).parent('div').remove();
//     })
//
// });

//---------- Stores results ---------- //
$('#loadStores').click(() => {
    reloadResults()
});

function reloadResults() {
    $("#stores-container").empty();

    fetch("http://localhost:8080/stores-results/week").then(response => response.json()).then(json => json.forEach(store => {
        let tableRow = '<tr>' +
            '<td>' + store.year + '</td>' +
            '<td>' + store.weekOfYear + '</td>' +
            '<td>' + store.storeName + '</td>' +
            '<td>' + store.visitors + '</td>' +
            '<td>' + store.countOfSales + '</td>' +
            '<td>' + store.countOfProducts + '</td>' +
            '<td>' + store.turnover + '</td>' +
            '<td>' + store.percentageSales + '</td>' +
            '<td>' + store.averagePricePerBasket + '</td>' +
            '<td>' + store.averagePricePerProducts + '</td>' +
            '<td>' + store.upt + '</td>' +
            '</tr>'

        $("#stores-container").append(tableRow)
    }))
}