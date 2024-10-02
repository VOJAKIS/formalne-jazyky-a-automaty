#include <stdio.h>
#include <stdlib.h>

int getLength(char* arr) {
	int length = 0;
	while (arr[length] != '\0') {
		length++;
	}
	return length;
}

int main() {
	char regex[] = "{a|b}";
	char input[] = "aababbaababababababbabababc";
	int length = getLength(input);

	// printf("Input a valid regexxxx: ");
	// fgets(regex, sizeof(regex), stdin);
	printf("RegEx: %s\n", regex);
	printf("Input string: %s\n", input);
	printf("Input string length: %d\n", length);

	// Add regex chars to valid character array
	char valid_characters[1000];
	int i = 0;
	while (regex[i] != '\0') {
		char c = regex[i];
		if ('|' == c) {}
		i++;
	}

	i = 0;
	while (input[i] != '\0') {
		char c = input[i];
		if ('a' == c || 'b' == c ) {
			printf("char %c is correct\n", c);
		}
		i++;
	}


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