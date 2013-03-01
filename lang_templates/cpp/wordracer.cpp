#include<iostream>
#include<fstream>
#include<set>

#define WIDTH 7
#define loc(x,y) x+y*WIDTH

using namespace std;
set<string> words;

void pick_letter(void);
void place_letter(char c);
void init(char c);

// ---- TEMPLATE CODE STARTS HERE
void build_wordlist() {
  ifstream wordfile("words.dat");
  string word;
  while(wordfile>>word){
    words.insert(word);
  }
  wordfile.close();
}

void place(char letter,int location) {
  cout<<letter<<" "<<location<<endl;
}

void place(int location) {
  cout<<location<<endl;
}

int main(){
  int no_of_turns = WIDTH*WIDTH - 1;
  char oppchar;
  int turn;

  build_wordlist();

  cin>>oppchar>>turn;
  place(loc(WIDTH/2,WIDTH/2));
  init(oppchar);

  if(turn){
    pick_letter();
    no_of_turns--;
  }

  while(1){
    if(!no_of_turns)  break;
    cin>>oppchar;
    place_letter(oppchar);

    no_of_turns--;

    if(!no_of_turns)  break;
    pick_letter();

    no_of_turns--;
  }
  return 0;
}
//---- TEMPLATE CODE ENDS HERE

void pick_letter() {
  // TODO: Fill in this method
  place('a', loc(1,1));
}

void place_letter(char letter) {
  // TODO: Fill in this method
  place(loc(1,1));
}

void init(char letter) {
  // Custom init code here, with the first character
}
