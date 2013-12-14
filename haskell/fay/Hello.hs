module Hello where

import FFI
import Prelude

alert :: String -> Fay ()
alert = ffi "alert(%1)"

setInnerHtml :: String -> String -> Fay ()
setInnerHtml = ffi "document.getElementById(%1).innerHTML = %2" 

setBodyHtml :: String -> Fay ()
setBodyHtml = ffi "document.body.innerHTML = %1"

addWindowEvent :: String -> Fay () -> Fay ()
addWindowEvent = ffi "window.addEventListener(%1, %2)"

getElem :: String -> String
getElem = ffi "document.getElementById(%1).innerHTML"


main :: Fay ()
main = do
  putStrLn "Hello Console!"
  alert "Hello Alert!"
  putStrLn $ getElem "greeting"
--  setInnerHtml "greeting" "Hello element"
  addWindowEvent "load" $ do
    putStrLn "The document has loaded"
    setBodyHtml "Hello HTML!"   
