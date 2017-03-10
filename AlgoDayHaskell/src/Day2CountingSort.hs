
module Day2CountingSort where

import Data.Array

countingSort :: (Ix n) => [n] -> n -> n -> [n]
countingSort l lo hi = concat [replicate times n | (n, times) <- assocs $ accumArray (+) 0 (lo, hi) [(i, 1) | i <- l]]
