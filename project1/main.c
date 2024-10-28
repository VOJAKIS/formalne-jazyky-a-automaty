#include <stdio.h>
#include <stdlib.h>

// [a]b{a|b}
/*
q0,a -> q1
q0,b -> q2
q1,a -> ⊥
q1,b -> q2
q2,a -> q2
q2,b -> q2
q2 -> finished
*/

int getLength(char* arr) {
	int length = 0;
	while (arr[length] != '\0') {
		length++;
	}
	return length;
}

char getFirstCharacter(char* arr) {
	return arr[0];
}

void consumeFirstCharacter(char* arr) {
	int arrayLength = getLength(arr);
	for (int i = 0; i<arrayLength-1; i++) {
		arr[i] = arr[i+1];
	}
	arr[arrayLength] = '\0';
}

void q0(char* arr) {
	char firstCharacter = getFirstCharacter(arr);
	consumeFirstCharacter(arr);
	if (firstCharacter == 'a') {
		q1(arr);
		return;
	}
	if (firstCharacter == 'b') {
		q1(arr);
		return;
	}
	printf("N");
}

void q1(char* arr) {
	char firstCharacter = getFirstCharacter(arr);
	consumeFirstCharacter(arr);
	// if (firstCharacter == 'a') {
	// 	q1(arr);
	// }
}

int main() {
	char input[] = "aababbaababababababbabababc";
	int length = getLength(input);

	// printf("Input a valid regexxxx: ");
	// fgets(regex, sizeof(regex), stdin);
	printf("Input string: %s\n", input);
	printf("Input string length: %d\n", length);

	// Add regex chars to valid character array
	// char valid_characters[1000];
	// int i = 0;
	// while (regex[i] != '\0') {
	// 	char c = regex[i];
	// 	if ('|' == c) {}
	// 	i++;
	// }

	// i = 0;
	// while (input[i] != '\0') {
	// 	char c = input[i];
	// 	if ('a' == c || 'b' == c ) {
	// 		printf("char %c is correct\n", c);
	// 	}
	// 	i++;
	// }


	q0(input);
	printf("Final string: %s\n", input);
	

	int seconds = 5;
	printf("\n");
	printf("\n");
	// printf("Exiting in %d seconds.", seconds);
	// printf("\n");
	// sleep(seconds);
	printf("Press any key to continue...");
	getchar();
	return 0;
}