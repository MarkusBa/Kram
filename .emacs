(custom-set-variables
 ;; custom-set-variables was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(show-paren-mode t))
(custom-set-faces
 ;; custom-set-faces was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 )

(require 'package)
(add-to-list 'package-archives 
      '("marmalade" . "http://marmalade-repo.org/packages/"))
(package-initialize)


(setq-default inhibit-startup-screen t)

;; Of course, don't uncomment the line below -- doing so would
;; stop Emacs from helpfully leaving "foo~" (backup) files all
;; over the place.
;(setq make-backup-files nil)

;; Use only spaces (no tabs at all).
(setq-default indent-tabs-mode nil)

;; Always show column numbers.
(setq-default column-number-mode t)

;; Display full pathname for files.
(add-hook 'find-file-hooks
          '(lambda ()
             (setq mode-line-buffer-identification 'buffer-file-truename)))

;; For easy window scrolling up and down.
(global-set-key "\M-n" 'scroll-up-line)
(global-set-key "\M-p" 'scroll-down-line)

;; For easier regex search/replace.
(defalias 'qrr 'query-replace-regexp)

;; My own preference. Change or comment out the following lines if you like.
;;(load-theme 'deeper-blue t)
;;(set-background-color "#383838")
(require 'rainbow-delimiters)

(global-rainbow-delimiters-mode)

(load-theme 'solarized-light t)

(load "/usr/share/emacs24/site-lisp/haskell-mode/haskell-mode.elc")

(add-hook 'haskell-mode-hook 'turn-on-haskell-doc-mode 
   'turn-on-haskell-indent)

(add-to-list 'load-path "~/.emacs.d/elpa/evil-1.0.6")
(require 'evil)
(evil-mode 1)

(tool-bar-mode 1)
(menu-bar-mode 1)



;;(defun cljs-repl ()
;;    (interactive)
;;    (setq inferior-lisp-program "cljs-repl")
;;    (run-lisp))

;;(setq inferior-lisp-program "/home/markus/clojurescript/script/browser-repl")
(setq inferior-lisp-program "/usr/local/bin/sbcl --noinform")

(set-variable (quote scheme-program-name) "gsi")
