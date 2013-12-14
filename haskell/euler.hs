divBy :: Int -> Int -> Bool
divBy x y = y `mod` x == 0

divBy3or5 :: Int -> Bool
divBy3or5 x = divBy 3 x || divBy 5 x

euler1 :: Int -> Int
euler1 n = sum $ filter divBy3or5 (take n [0..])

main :: IO()
main = putStrLn $ show $ euler1 1000
