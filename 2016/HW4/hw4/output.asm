li $t0,4

li $t1,3

li $t2,12

li $t3,3
mult $t1,$t3
mflo $t3
sub $t3,$t0,$t3
move $t1,$t3

li $t3,3
div $t0,$t3
mflo $t3
mult $t3,$t1
mflo $t3
li $t4,21
add $t5,$t3,$t4
move $t2,$t5

move $a0,$t2
li $v0,1
syscall
