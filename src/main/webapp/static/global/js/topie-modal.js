/**
 * Created by chenguojun on 8/29/16.
 */
;
(function ($, window, document, undefined) {
    var Modal = function (options) {
        this._setVariable(options);
        this._setOptions(this._options);
        this._init();
    };
    Modal.defaults = {
        render: "body",
        title: "视窗",
        color: "#6887c4",
        showClose: true,
        destroy: true,
        backdrop: true,
        scroll: true,
        keyboard: false,
        maxHeight: "550px",
        minHeight: "450px",
        width: "75%"
    };
    Modal.statics = {
        modalTmpl: '<div id="${id_}" class="modal fade container ${scroll_}" style="border-radius:0;border: 1px solid ${color_};" ${backdrop_} ${keyboard_}></div>',
        headerTmpl: '<div id="${id_}head" class="modal-header" style="background-color: ${color_};"></div>',
        closeBtnTmpl: '<button type="button" class="close" data-dismiss="modal" style="opacity: 1;color: white;" aria-hidden="true">X</button>',
        titleTmpl: '<h6 style="font-size: 14px;color: #ffffff" id="${id_}title" class="modal-title" >${title_}</h6>',
        bodyTmpl: '<div  id="${id_}body" class="modal-body"><div class="panel-body"></div></div>',
        footerTmpl: '<div id="${id_}footer" class="modal-footer"></div>',
        buttonTmpl: '<button type="button" ${attrbute_} class="btn ${cls_}">${text_}</button>'
    };
    Modal.prototype = {
        show: function () {
            this.$modal.modal(this.modalOpts);
            return this;
        },
        hide: function () {
            this.$modal.modal("hide");
            return this;
        },
        content: function (html) {
            this.$body.html(html);
            return this;
        },
        _setVariable: function (options) {
            this._options = options;
            this.$modal = new Object();
            this.$body = new Object();
            this.modalOpts = {};
        },
        _setOptions: function (options) {
            if (options.id == undefined) {
                this._elementId = "topie_modal_" + new Date().getTime();
            } else {
                this._elementId = options.id;
            }
            this._color = options.color;
            this._title = options.title;
            this._showClose = options.showClose;
            this._destroy = options.destroy;
            this._render = options.render;
            this.$render = $(this._render);
            this._buttons = options.buttons;
            this._backdrop = options.backdrop;
            this._scroll = options.scroll;
            this._keyboard = options.keyboard;
            if (options.width != undefined) {
                this.modalOpts.width = options.width;
            }
            if (options.height != undefined) {
                this.modalOpts.height = options.height;
            }
            if (options.minHeight != undefined) {
                this.modalOpts.minHeight = options.minHeight;
            }
            if (options.maxHeight != undefined) {
                this.modalOpts.maxHeight = options.maxHeight;
            }
        },
        _init: function () {
            this._renderEle();
            this._registerEvents();
        },
        _registerEvents: function () {
            var that = this;
            if (this._destroy) {
                this.$modal.on('hidden', function () {
                    that._destroyModal();
                });
            }
        },
        _renderEle: function () {
            var that = this;
            var modal = $.tmpl(Modal.statics.modalTmpl, {
                "id_": that._elementId,
                "scroll_": that._scroll ? "modal-scroll" : "",
                "backdrop_": that._backdrop ? "data-backdrop=static" : "",
                "keyboard_": that._keyboard ? "data-keyboard=true" : "data-keyboard=false",
                "color_": that._color
            });
            this.$render.append(modal);
            this.$modal = modal;
            // header
            var header = $.tmpl(Modal.statics.headerTmpl, {
                "id_": that._elementId,
                "color_": that._color
            });
            if (this._showClose) {
                var close = $.tmpl(Modal.statics.closeBtnTmpl, {});
                header.append(close);
            }
            var title = $.tmpl(Modal.statics.titleTmpl, {
                "id_": that._elementId,
                "title_": that._title
            });
            header.append(title);
            modal.append(header);

            // body
            var body = $.tmpl(Modal.statics.bodyTmpl, {
                "id_": that._elementId
            });
            modal.append(body);
            this.$body = body.find("div.panel-body");

            // footer
            if (this._buttons != undefined) {
                var footer = $.tmpl(Modal.statics.footerTmpl, {
                    "id_": that._elementId
                });
                $.each(this._buttons, function (i, button) {
                    var attribute = "";
                    if (button.hideModal) {
                        attribute += " data-dismiss=modal ";
                    }
                    if (button.attribute != undefined) {
                        attribute += button.attribute;
                    }
                    var btn = $.tmpl(Modal.statics.buttonTmpl, {
                        "attrbute_": attribute,
                        "cls_": button.cls == undefined ? "" : button.cls,
                        "text_": button.text == undefined ? "未定义" : button.text
                    });
                    if (button.handle != undefined) {
                        btn.on("click", function () {
                            button.handle();
                        });
                    }
                    footer.append(btn);
                });
                modal.append(footer);
            }

        },
        _destroyModal: function () {
            if (typeof (KindEditor) != "undefined") {
                KindEditor.remove('textarea[role=kindEditor]');
            }
            this.$modal.remove();
        }
    };

    /**
     * jquery插件扩展 ===================================================
     */

    var modal = function (options) {
        options = $.extend(true, {}, Modal.defaults, options);
        var instance = new Modal(options);
        return instance;
    };

    $.extend({
        'topieModal': modal
    });
})(jQuery, window, document);
