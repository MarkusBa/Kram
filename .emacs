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

(defvar my-packages '(starter-kit
                      starter-kit-lisp
                      starter-kit-bindings
                      starter-kit-eshell
                      clojure-mode
                      clojure-test-mode
                      nrepl))

(dolist (p my-packages)
  (when (not (package-installed-p p))
    (package-install p)))


(add-to-list 'package-archives
             '("melpa" . "http://melpa.milkbox.net/packages/") t)

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

(require 'paredit)
(paredit-mode 1)

(load-theme 'solarized-light t)

;; haskell
;;(load "/usr/share/emacs24/site-lisp/haskell-mode/haskell-mode.elc")

;; haskell
;;(add-hook 'haskell-mode-hook 'turn-on-haskell-doc-mode 'turn-on-haskell-indent)
(add-hook 'haskell-mode-hook 'my-haskell-mode-hook)

(defun my-haskell-mode-hook ()
   (haskell-indentation-mode 1) ;; turn off, just to be sure
   (haskell-indent-mode -1)       ;; turn on indent-mode

   ;; further customisations go here.  For example:
   (setq locale-coding-system 'utf-8 )
   ;;(flyspell-prog-mode)  ;; spell-checking in comments and strings
   ;; etc.      

)


;; evil
;;(add-to-list 'load-path "~/.emacs.d/elpa/evil-1.0.6")
;;(require 'evil)
;;(evil-mode 1)

(tool-bar-mode 1)
(menu-bar-mode 1)


;; nrepl
(add-hook 'nrepl-interaction-mode-hook 'nrepl-turn-on-eldoc-mode)
(setq nrepl-popup-stacktraces nil)
(add-to-list 'same-window-buffer-names "*nrepl*")
(add-hook 'nrepl-mode-hook 'paredit-mode)

(global-set-key [f9] 'nrepl-jack-in)

;; Auto complete
(require 'auto-complete-config)
(ac-config-default)
(define-key ac-completing-map "\M-/" 'ac-stop) ; use M-/ to stop completion

;; ac-nrepl
(require 'ac-nrepl)
(add-hook 'nrepl-mode-hook 'ac-nrepl-setup)
(add-hook 'nrepl-interaction-mode-hook 'ac-nrepl-setup)
(eval-after-load "auto-complete" '(add-to-list 'ac-modes 'nrepl-mode))

(package-initialize)
(require 'auto-complete)
(global-auto-complete-mode)

;; clojurescript
;;(defun cljs-repl ()
;;    (interactive)
;;    (setq inferior-lisp-program "cljs-repl")
;;    (run-lisp))

;;(setq inferior-lisp-program "/home/markus/clojurescript/script/browser-repl")
;;(setq inferior-lisp-program "/usr/local/bin/sbcl --noinform")


;; scheme
;;(set-variable (quote scheme-program-name) "gsi")


(global-hl-line-mode t)
;; Shows in modeline where in the file you are
;;(sml-modeline-mode)
;; Pretty symbols
(require 'pretty-mode-plus)
(global-pretty-mode 1)
;; Autocomplete
(require 'auto-complete)
;; Disable annoying bell sound
;;(setq visible-bell t)
;; Disables scrollbar
;;(setq scroll-bar-mode nil)
;; Disables toolbar
;;(setq tool-bar-mode nil)
;; Optional: If you've installed solarized-theme
;;(require 'solarized-dark-theme) ;; Also as solarized-light-theme
 
;; ido completion
(setq ido-enable-flex-matching t)
(setq ido-everywhere t)
(ido-mode 1)
;;(setq ido-mode (quote both) nil (ido))
(setq ido-ubiquitous-mode t)
 
;; Enable SMEX, an enhancement for M-x completion
(global-set-key [(meta x)] (lambda ()
(interactive)
(or (boundp 'smex-cache)
(smex-initialize))
(global-set-key [(meta x)] 'smex)
(smex)))
(global-set-key [(shift meta x)] (lambda ()
(interactive)
(or (boundp 'smex-cache)
(smex-initialize))
(global-set-key [(shift meta x)] 'smex-major-mode-commands)
(smex-major-mode-commands)))
 
;; Unicode as default
;; set up unicode
(prefer-coding-system 'utf-8)
(set-default-coding-systems 'utf-8)
(set-terminal-coding-system 'utf-8)
(set-keyboard-coding-system 'utf-8)
;; This from a japanese individual. I hope it works.
(setq default-buffer-file-coding-system 'utf-8)
;; From Emacs wiki
(setq x-select-request-type '(UTF8_STRING COMPOUND_TEXT TEXT STRING))




;; clj-refactor: Use C-c C-m. See https://github.com/magnars/clj-refactor.el
(defun clj-refactor-hooks ()
(clj-refactor-mode 1)
(cljr-add-keybindings-with-prefix "C-c C-m"))
 
;; nREPL: The new Clojure network REPL. See https://github.com/kingtim/nrepl.el
(require 'nrepl)
(add-hook 'nrepl-interaction-mode-hook 'nrepl-turn-on-eldoc-mode)
 
;; Autocompletion for nREPL; From https://github.com/purcell/ac-nrepl
(require 'ac-nrepl)
(add-hook 'nrepl-mode-hook 'ac-nrepl-setup)
(add-hook 'nrepl-interaction-mode-hook 'ac-nrepl-setup)
(eval-after-load "auto-complete"
'(add-to-list 'ac-modes'nrepl-mode))
;; Probably gets in the way of yasnippet?
(defun set-auto-complete-as-completion-at-point-function ()
(setq completion-at-point-functions '(auto-complete)))
(add-hook 'auto-complete-mode-hook 'set-auto-complete-as-completion-at-point-function)
(add-hook 'nrepl-mode-hook 'set-auto-complete-as-completion-at-point-function)
(add-hook 'nrepl-interaction-mode-hook 'set-auto-complete-as-completion-at-point-function)
;; Popup documentation change
(define-key nrepl-interaction-mode-map (kbd "C-c C-d") 'ac-nrepl-popup-doc)
 
;; Glue code: Bring it all together...
(add-hook 'clojure-mode-hook 'paredit-mode)
(add-hook 'clojure-mode-hook 'rainbow-delimiters-mode)
(add-hook 'clojure-mode-hook 'auto-indent-mode)
(add-hook 'clojure-mode-hook 'clj-refactor-hooks)
(add-hook 'clojure-mode-hook 'auto-complete-mode)
(add-to-list 'auto-mode-alist '("\\.cljs\\'" . clojure-mode))
(add-to-list 'auto-mode-alist '("\\.cljx\\'" . clojure-mode))
(add-to-list 'auto-mode-alist '("\\.clj\\'" . clojure-mode))

;; turn off asking for server, login etc.
(require 'sql)
(defalias 'sql-get-login 'ignore)

(defvar mysql-user "root")
(defvar mysql-password "1234")


(setq sql-user "root")
(setq sql-password "1234")

(require 'mysql)


   (require 'sql-completion)
   (setq sql-interactive-mode-hook
         (lambda ()
           (define-key sql-interactive-mode-map "\t" 'comint-dynamic-complete)
           (sql-mysql-completion-init)))

;; mysql history
 (defun my-sql-save-history-hook ()
    (let ((lval 'sql-input-ring-file-name)
          (rval 'sql-product))
      (if (symbol-value rval)
          (let ((filename 
                 (concat "~/.emacs.d/sql/"
                         (symbol-name (symbol-value rval))
                         "-history.sql")))
            (set (make-local-variable lval) filename))
        (error
         (format "SQL history will not be saved because %s is nil"
                 (symbol-name rval))))))
 
  (add-hook 'sql-interactive-mode-hook 'my-sql-save-history-hook)

;;immer my
(sql-set-product 'mysql)

;;java
;(add-to-list 'load-path "~/.emacs.d/lisp/")
  (add-to-list 'load-path "~/.emacs.d/jdee/lisp" )
  (autoload 'jde-mode "jde" "JDE mode" t)
  (setq auto-mode-alist
        (append '(("\\.java\\'" . jde-mode)) auto-mode-alist))



;; disable color crap
(global-font-lock-mode nil)

(defun insert-p-tag ()
  "Insert <p></p> at cursor point."
  (interactive)
  (insert "<p></p>")
  (backward-char 4))

(defun next-user-buffer ()
  "Switch to the next user buffer.
User buffers are those whose name does not start with *."
  (interactive)
  (next-buffer)
  (let ((i 0))
    (while (and (string-equal "*" (substring (buffer-name) 0 1)) (< i 20))
      (setq i (1+ i)) (next-buffer))))

(defun previous-user-buffer ()
  "Switch to the previous user buffer.
User buffers are those whose name does not start with *."
  (interactive)
  (previous-buffer)
  (let ((i 0))
    (while (and (string-equal "*" (substring (buffer-name) 0 1)) (< i 20))
      (setq i (1+ i)) (previous-buffer) )))



(require 'god-mode)
(global-set-key (kbd "<escape>") 'god-mode-all)
(defun my-update-cursor ()
  (setq cursor-type (if (or god-local-mode buffer-read-only)
                        'box
                      'bar)))

(add-hook 'god-mode-enabled-hook 'my-update-cursor)
(add-hook 'god-mode-disabled-hook 'my-update-cursor)

(defun fullscreen (&optional f)
       (interactive)
       (x-send-client-message nil 0 nil "_NET_WM_STATE" 32
               '(2 "_NET_WM_STATE_MAXIMIZED_VERT" 0))
       (x-send-client-message nil 0 nil "_NET_WM_STATE" 32
               '(2 "_NET_WM_STATE_MAXIMIZED_HORZ" 0)))

(shell)

    (defun kill-other-buffers ()
      "Kill all other buffers."
      (interactive)
      (mapc 'kill-buffer (delq (current-buffer) (buffer-list))))

;;Irc client
(require 'erc)

(erc-autojoin-mode t)
(setq erc-autojoin-channels-alist
  '((".*\\.freenode.net" "#emacs" "#clojure" "#lisp" "#ubuntu-de" "##java" "#nixos" "#haskell")
     ))

(erc-track-mode t)
(setq erc-track-exclude-types '("JOIN" "NICK" "PART" "QUIT" "MODE"

                                 "324" "329" "332" "333" "353" "477"))
;; don't show any of this
(setq erc-hide-list '("JOIN" "PART" "QUIT" "NICK"))

(defun djcb-erc-start-or-switch ()
  "Connect to ERC, or switch to last active buffer"
  (interactive)
  (if (get-buffer "irc.freenode.net:6667") ;; ERC already active?

    (erc-track-switch-buffer 1) ;; yes: switch to last active
    (when (y-or-n-p "Start ERC? ") ;; no: maybe start ERC
      (erc :server "irc.freenode.net" :port 6667 :nick "MarkusBarthlen" :full-name "Markus" :password "mark" )
      )))

;; alt: MarkusX

(setq confirm-nonexistent-file-or-buffer nil)

;; integrate copy/paste with X
(setq x-select-enable-clipboard t)
(setq interprogram-paste-function 'x-cut-buffer-or-selection-value)

(require 'anything-grep)
(require 'undo-tree)

;minibuffer hisT
(savehist-mode 1)


(require 'org)
(define-key global-map "\C-cl" 'org-store-link)
(define-key global-map "\C-ca" 'org-agenda)
(setq org-log-done t)

(setq org-agenda-files (list "~/org/work.org"
                             "~/org/home.org"))

(setq org-default-notes-file (concat org-directory "/org/notes.org"))
(define-key global-map "\C-cc" 'org-capture)

     (setq org-capture-templates
      '(("t" "Todo" entry (file+headline "~/org/gtd.org" "Tasks")
             "* TODO %?\n  %i\n  %a")
        ("j" "Journal" entry (file+datetree "~/org/journal.org")
             "* %?\nEntered on %U\n  %i\n  %a")))

(setq org-startup-indented t)
(require 'recentf)
 
;; get rid of `find-file-read-only' and replace it with something
;; more useful.
(global-set-key (kbd "C-x C-r") 'ido-recentf-open)
 
;; enable recent files mode.
(recentf-mode t)
 
; 50 files ought to be enough.
(setq recentf-max-saved-items 50)
 
(defun ido-recentf-open ()
  "Use `ido-completing-read' to \\[find-file] a recent file"
  (interactive)
  (if (find-file (ido-completing-read "Find recent file: " recentf-list))
      (message "Opening file...")
    (message "Aborting")))

;;(add-to-list 'load-path "~/git/slime")
;;(require 'slime-autoloads)

;; Set your lisp system and, optionally, some contribs
;;(setq inferior-lisp-program "/usr/bin/sbcl")
;(setq slime-contribs '(slime-fancy))

(flyspell-mode 0)
(add-hook 'org-mode-hook (lambda () (flyspell-mode -1)))

;; switch to ERC with Ctrl+c i
(global-set-key (kbd "C-c i") 'djcb-erc-start-or-switch) ;; ERC  
(global-set-key "\C-cn" 'next-user-buffer)
(global-set-key "\C-cs" 'shell)
(global-set-key "\C-cq" 'sql-mode)
(global-set-key "\C-cj" 'jde-mode)
(global-set-key "\C-cv" 'evil-mode)
(global-set-key "\C-cp" 'paredit-mode)
(global-set-key "\C-cd" 'dired)
(global-set-key "\C-cg" 'global-font-lock-mode)
(global-set-key "\C-cb" 'ido-switch-buffer)
(global-set-key (kbd "<f8>") 'ido-switch-buffer)   
(global-set-key [f11] 'fullscreen)
(global-set-key "\C-cf" 'list-matching-lines)
(global-set-key "\M-o" 'other-window)
(global-set-key [f2] 'rgrep)
(global-set-key [f1] 'occur)
(global-set-key "\C-x\C-m" 'execute-extended-command)
(global-set-key "\C-f" 'scroll-up)
(global-set-key "\C-b" 'scroll-down)

;;(global-set-key "\C-Z" nil)
(global-set-key "\C-V" 'yank)
(global-set-key "\C-cc" 'kill-ring-save)
(setq x-select-enable-clipboard t)
(setq interprogram-paste-function 'x-cut-buffer-or-selection-value)


(fullscreen)
