(function ($) {

    window.app = {

        init: function () {

            this.is_ipad = navigator.userAgent.indexOf('iPad') > -1;
            this.is_iphone = navigator.userAgent.indexOf('iPhone') > -1;

            return true;
        },

        spritely: {
            init: function () {
                // spritely methods...               
                $('#flubber')
                    .sprite({ fps: 10, no_of_frames: 2 })
                    .isDraggable({
                        start: function () {
                            if (!$.browser.msie) {
                                $('#flubber').fadeTo('fast', 0.7);
                            }
                        },
                        stop: function () {
                            if (!$.browser.msie) {
                                $('#flubber').fadeTo('slow', 1);
                            }
                        }
                    })
                    .activeOnClick();
                    $('#flubber').spRelSpeed(8);

                // $('html').flyToTap();
                if (!window.app.is_ipad && document.location.hash.indexOf('iphone') > -1) {
                    // iPhone/iPad
                    $('body').addClass('platform-iphone');
                    // bird constraint is slightly smaller
                    //$('#bird').spRandom({ top: -10, left: -10, right: 150, bottom: 100, speed: 3500, pause: 5000 });
                    $('#flubber').spRandom({ top: 70, left: 100, right: 200, bottom: 340, speed: 4000, pause: 3000 });
                    if (document.location.hash.indexOf('iphone') > -1) {
                        $('body').addClass('platform-iphone');
                    }
                } else {
                    // non-iPhone
                    // bird constraint is slightly wider
                    var stage_left = (($('body').width()) / 2);
                    var stage_top = 30;
                    //$('#bird').spRandom({ top: stage_top - 20, left: stage_left - 20, right: 400, bottom: 140, speed: 3500, pause: 5000 });
                    $('#bird21').spRandom({ top: stage_top + 0, right: stage_left + 100, left: 200, bottom: 340, speed: 4000, pause: 3000 });
                }
                if (window.app.is_ipad) {
                    $('#dragMe, .ui-slider-handle').hide();
                    $('#noFlash').css({
                        'top': '185px',
                        'right': '20px'
                    });
                    $('#sprite_up').css({
                        'top': '300px',
                        'left': '30px'
                    });
                    $('#container, .stage').css({
                        'min-width': '768px'
                    });
                } else {
                    if (window.app.is_iphone || document.location.hash.indexOf('iphone') > -1) {
                        $('#container, .stage').css({
                            'min-width': '300px'
                        });
                    }
                    else {
                        $('#container, .stage').css({
                            'min-width': '900px'
                        });
                    }
                }
            }
        }
    };


    $(document).ready(function () {

        window.app.init();
        window.app.spritely.init();
        //window.app.contactForm.init();
    });


})(jQuery);


