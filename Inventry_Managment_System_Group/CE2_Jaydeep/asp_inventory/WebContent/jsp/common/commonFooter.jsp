<footer>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    &copy; 2015 YourCompany | By : <a href="http://www.designbootstrap.com/" target="_blank">DesignBootstrap</a>
                </div>

            </div>
        </div>
    </footer>
    <script>
$(".items > li > a").click(function(e) {
    e.preventDefault();
    var $this = $(this);
    if ($this.hasClass("expanded")) {
        $this.removeClass("expanded");
    } else {
        $(".items a.expanded").removeClass("expanded");
        $this.addClass("expanded");
        $(".sub-items").filter(":visible").slideUp("normal");
    }
    $this.parent().children("ul").stop(true, true).slideToggle("normal");
});

$(".sub-items a").click(function() {
    $(".sub-items a").removeClass("current");
    $(this).addClass("current");
});
</script>