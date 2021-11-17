$(document).ready(function () {
    let max_fields = 100;
    let index = 1;


    $('.add-product').click(function () {

        if (index < max_fields) {
            let newRow = jQuery('<div class="col-auto"> ' +
                ' <label for="product">Product</label> ' +
                ' <select th:field="*{products}" id="product" class="custom-select" ' +
                ' <option value="">Select Product</option> ' +
                ' <option th:each="p : ${products}" ' +
                'th:value="${p.name}" ' +
                'th:text="|${p.category}: ${p.name} ${p.price}|"> ' +
                ' </option> ' +
                '  </select> ' +
                '</div>');
            index++;

            $('.products').append(newRow)
        }
    });
});