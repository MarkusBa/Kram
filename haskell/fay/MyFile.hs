{-# LANGUAGE NoImplicitPrelude #-}
{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE RebindableSyntax #-}

module Test (main) where

import Fay.Text (Text, fromString)
import JQuery
import Prelude hiding (div)

(>=>) :: (a -> Fay b) -> (b -> Fay c) -> (a -> Fay c)
f >=> g = \x -> f x >>= g


makeSquare :: JQuery -> Fay JQuery
makeSquare = addClass "square" >=>
             setWidth 400 >=>
             setHeight 400

main :: Fay ()
main = do
    img <- select "#elementToMakeSquare"
    makeSquare img
    return ()
