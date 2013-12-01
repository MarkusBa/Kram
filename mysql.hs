 import Control.Monad
 import Database.HDBC
 import Database.HDBC.MySQL

 test x = case (fromSql x)::Maybe Integer of
            Just t -> t + 1
	    Nothing -> 0
	
 test2 x = case fromSql x of
            Just t -> t
	    Nothing -> "Null"
  


 main = do conn <- connectMySQL defaultMySQLConnectInfo {
                        mysqlDatabase = "haskell",
                        mysqlPassword = "1234"
                     }

           rows <- quickQuery' conn "SELECT * FROM example" []
           forM_ rows $ \row -> putStrLn $ show $ test $ head row
