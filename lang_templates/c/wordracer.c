#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define ROWS 7
#define COLUMNS 7
#define WORDCOUNT 50000

typedef struct RESULT{
    char letter;
    int position;
}Result;

static char board[ROWS][COLUMNS];

static char* words[WORDCOUNT];
static int num_words = 0;

Result pick_letter(void);
int place_letter(char letter);
void init(char letter);

//---- TEMPLATE CODE STARTS HERE
void build_wordlist(char* filename){
    FILE *wordfp = fopen(filename, "r");
    char* line = NULL;
    size_t len = 0;
    ssize_t read;
    int i=0;

    if(NULL == wordfp){
        fprintf(stderr,"words.dat file not found\n");
        exit(-1);
    }
    
    // getline() reads an entire line, storing the address of the buffer 
    // containing the text into *lineptr. The buffer is null-terminated 
    // and includes the newline character, if a newline delimiter was found.
    while((read = getline(&line, &len, wordfp)) != -1){
        words[i] = (char *)malloc(sizeof(char) * read);
        memcpy(words[i], line, read-1);
        // overwriting the new line
        words[i][read-1] = '\0';
        ++i;
    }
    
    num_words = i;    

    if(line){
        free(line);
        line = NULL;
    }

    fclose(wordfp);
    wordfp = NULL;
}

void init_board(char letter){
    int i = 0, j = 0;
    for(i=0;i<ROWS;++i){
        for(j=0;j<COLUMNS;++j){
            board[i][j]='*'; 
        }
    }
    board[3][3] = letter;
}

void print_wordlist(){
    int i = 0;
    for(;i < num_words; ++i){
        printf("-----> %s\n", words[i]);
    }
}

int main(){
    int my_turn = 0;
    int total_moves = 0;
    int num_moves = 0;
    // scan the first character from terminal
    char first_char;
    // scan next input from terminal - turn
    int input;
    char opponent_letter='\0';
    int opponent_letter_position;
    Result result;
    int char_position;
    
    build_wordlist("words.dat");

    scanf("%c", &first_char);
    scanf("%d", &input);

    init_board(first_char);
    init(first_char);
    my_turn = (1 == input);
    for(; num_moves < 24; ++num_moves){
        if(my_turn){
            result = pick_letter();
            printf("%c %d\n", result.letter, result.position);
        }else{
            // XXX: 
            // already hitting the ugly parts of stdin buffer handling 
            do{
                scanf("%c", &opponent_letter);
            }while(opponent_letter == '\n' || opponent_letter == '\0');
            printf("%d\n", place_letter(opponent_letter));
        }
        // toggle move
        my_turn = my_turn ^ 1;
    }
    
    return 0; 
}

//---- TEMPLATE CODE ENDS HERE

void init(char letter) {
    // Custom init code here, with the first character
}

Result pick_letter(){
    Result result = {'\0', -1};
    // TODO: Fill in this method
    return result;
}

int place_letter(char letter){
    // TODO: Fill in this method
    return 1;
}

