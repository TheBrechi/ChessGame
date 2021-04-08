# ChessGame

Code developped with eclipse. Go to src to find java files.

First game of chess developped in Java

For the input command: target piece code + (precision) + target case.

Target piece code: Pawn = ""; Knight = "n"; Bishop = "b"; Rook = "r"; Queen = "q"; King = "k";

Precision: If several pieces can go on the target case you must precise either the row or the column (which can clearly identify the target piece) of the piece you want to execute the movement.

Target Case: Columns go from a to h. Rows go from 1 to 8.

Examples: You want the pawn on column e to go on e4, as it's the only pawn allowed to go on this case, the movement input is e4 ("" + "" + e4). Both black knights can go on the case d7, you want the one on column b to execute this movement: input is nbd7 (n + b + d7).
