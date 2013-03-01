import sys

SIZE = 7
EMPTY = '_'
board = [[EMPTY]*SIZE for n in xrange(SIZE)]

WORDS = []

def build_wordlist():
    global WORDS
    WORDS = [line.strip() for line in open('words.dat')]

def xy_to_num(x, y):
    return (y * SIZE) + x

def print_board():
    xys = [(i,j) for i in xrange(SIZE) for j in xrange(SIZE)]
    count = 1
    for x,y in xys:
        sys.stdout.write(board[y][x])
        if count%SIZE == 0: print ''
        count += 1
    print '\n'

def loop_and_play():
    while True:
        sys.stdout.write(pick_letter())
        sys.stdout.write('\n')
        sys.stdout.flush()

        opp_char = raw_input()
        sys.stdout.write(place_letter(opp_char))
        sys.stdout.write('\n')
        sys.stdout.flush()

def start():    
    build_wordlist()

    """
        The first line of input contains the starting letter which is automatically 
        placed in the center of the board (at position 24). 
    """
    starting_char = raw_input()
    board[SIZE/2][SIZE/2] = starting_char            
    """
        The second line of input specifies if you are the first one to start 
        or your opponent is starting first. A value of 1 indicates that you start 
        first and 0 otherwise.
    """
    first_or_second = raw_input()
    if first_or_second == '0':        
        opp_char = raw_input()
        sys.stdout.write(place_letter(opp_char))
        sys.stdout.write('\n')
        sys.stdout.flush()

    loop_and_play()

"""
    ---------------------------- TODO: implement your logic here --------------------------------
"""

def pick_letter():
    # TODO: Your chance to pick a letter of your choice
    return 'a' + ' ' + str(xy_to_num(0,1));

def place_letter(letter=None):
    # TODO: Place the letter at a position of your choice
    return str(xy_to_num(1, 2))

start()
