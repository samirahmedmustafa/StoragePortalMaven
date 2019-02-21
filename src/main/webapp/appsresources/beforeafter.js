$(document).ready(function () {

	if("ontouchstart" in window){
		$(".before-after-container").addClass("touch-events");
	}
	// Automatic on mouse hover
	$( ".beforeAfter-hover" ).each(function () {
		var $this = $( this );
		var img2 = $this.find( ".img2" ).get( 0 );
		var img1 = $this.find( ".img1" ).get( 0 );
		var border = $this.find( ".border" ).get( 0 );

		function trackLocation(e) {
			var rect = img1.getBoundingClientRect();
			var position = ( (e.pageX - rect.left - $(window).scrollLeft()) / img1.offsetWidth ) * 100;
			if ( position <= 100 ) {
				img2.style.width = position + "%";
				border.style.left = position + "%";
			}
		}
		function trackTouchLocation(e) {

			var coordX = e.originalEvent.touches[0].pageX;
			var rect = img1.getBoundingClientRect();
			var position = ( (coordX - rect.left - $(window).scrollLeft()) / img1.offsetWidth ) * 100;
			if ( position <= 100 ) {
				img2.style.width = position + "%";
				border.style.left = position + "%";
			}
		}

		img2.style.width = "50%";
		border.style.left = "50%";
		$this.on( "mousemove", trackLocation );
		$this.on( "touchstart touchmove", trackTouchLocation );
	});

	// Slide with handle
	$( ".beforeAfter-slide" ).each(function () {
		var $this = $( this );
		var img2 = $this.find( ".img2" ).get( 0 );
		var img1 = $this.find( ".img1" );
		var border = $this.find( ".border" ).get( 0 );
		var handle = $this.find( ".handle" );
		var scroll = false;

		var src = img1.attr("src");
		img1.attr( "src", "" ).on("load", resetCompare).attr( "src", src );

		function trackLocation(e) {
			if (scroll) {
				rect = img1.get( 0 ).getBoundingClientRect();
				var position = ( (e.pageX - rect.left - $(window).scrollLeft()) / img1.get(0).offsetWidth ) * 100;
				if ( position >= 0 && position <= 100 ) {
					img2.style.width = position + "%";
					border.style.left = position + "%";
					handle.get( 0 ).style.left = (e.pageX - rect.left - $(window).scrollLeft() - handle.width() / 2) + "px";
				}
			}
		}
		function trackTouchLocation(e) {
			if (scroll) {
				var coordX = e.originalEvent.touches[0].pageX;
				rect = img1.get( 0 ).getBoundingClientRect();
				var position = ( (coordX - rect.left - $(window).scrollLeft()) / img1.get(0).offsetWidth ) * 100;
				if ( position >= 0 && position <= 100 ) {
					img2.style.width = position + "%";
					border.style.left = position + "%";
					handle.get( 0 ).style.left = (coordX - rect.left - $(window).scrollLeft() - handle.width() / 2) + "px";
				}
				e.preventDefault();
			}
		}

		function resetCompare(){
			handle.css({
				"top": ( $(img1).height() / 2 - handle.height() / 2 ) + "px",
				"left": ( $this.width() / 2 - handle.width() / 2 ) + "px",
				"display": "block"
			});
			img2.style.width = "50%";
			border.style.left = "50%";
		}

		handle.on("mousedown touchstart", function () { scroll = true; }).on( "mouseup touchend", function () { scroll = false; });
		$this.on("mouseleave mouseup touchend", function () { scroll = false; });

		$this.on( "mousemove", trackLocation );
		$this.on( "touchstart touchmove", trackTouchLocation );
		$("#imContent").on("breakpointChangedOrFluid", function (e, breakpoint) { resetCompare(); });
	});

	// Slide with bar
	$( ".beforeAfter-fade" ).each(function () {
		var $this = $( this );
		var img2 = $this.find( ".img2" ).get( 0 );
		var img1 = $this.find( ".img1" );
		var border = $this.find( ".border" ).get( 0 );
		var handle = $this.find( ".handle" );
		var body = $( "body" );
		var scroll = false;

		var src = img1.attr("src");
		img1.attr( "src", "" ).on("load", resetCompare).attr( "src", src );

		function trackLocation(e) {
			if (scroll) {
				rect = img1.get( 0 ).getBoundingClientRect();
				var position = ( (e.pageX - rect.left - $(window).scrollLeft()) / img1.get(0).offsetWidth );
				if ( position >= 0 && position <= 1 ) {
					$(img2).css( "opacity", position );
					handle.get( 0 ).style.left = (e.pageX - rect.left - $(window).scrollLeft() - handle.width() / 2) + "px";
				}
			}
		}

		function trackTouchLocation(e) {
			var coordX = e.originalEvent.touches[0].pageX;
			if (scroll) {
				rect = img1.get( 0 ).getBoundingClientRect();
				var position = ( (coordX - rect.left - $(window).scrollLeft()) / img1.get(0).offsetWidth );
				if ( position >= 0 && position <= 1 ) {
					$(img2).css( "opacity", position );
					handle.get( 0 ).style.left = (coordX - rect.left - $(window).scrollLeft() - handle.width() / 2) + "px";
				}
				e.preventDefault();
			}
		}

		function resetCompare(){
			handle.css({
				"left": ($this.width() / 2 - handle.width() / 2) + "px",
				"top": "auto",
				"bottom": "-" + (handle.height() + 5) + "px",
				"display": "block"
			});
			$(border).css({
				width: "100%",
				height: "1px",
				top: "auto",
				left: 0,
				bottom: "-" + (handle.height() / 2 + 5) + "px",
			});
			img2.style.width = "100%";
			img2.style.opacity = 0.5;
		}

		handle.on("mousedown touchstart", function () {
			scroll = true;
			body.one("mouseup touchend", function () {
				scroll = false;
			});
		});
		body.on( "mouseleave touchend", function () { scroll = false; });

		body.on( "mousemove", trackLocation);
		$this.on( "touchstart touchmove", trackTouchLocation );
		$("#imContent").on("breakpointChangedOrFluid", function (e, breakpoint) { resetCompare(); });
	});
});
