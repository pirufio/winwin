
$(function () {
    //$(".datefield").datepicker({ dateFormat: "dd/MM/YYYY" });
    if (!Modernizr.inputtypes.date) {
        $(function () {
            $(".datefield").datepicker({ dateFormat: "dd/MM/YYYY" });
        });
    }
});