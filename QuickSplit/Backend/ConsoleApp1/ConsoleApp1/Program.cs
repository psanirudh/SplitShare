using System.Text;

Stack<char> word = new Stack<char>(); 
word.Append('a');
word.Push('b');
word.Append('c');

word.Push('d');
char[] chars = { 'H', 'e', 'l', 'l', 'o' };

Console.WriteLine(new string(new string(chars).Reverse().ToArray()));
Console.WriteLine(" "+test.ReverseWords("My, name. is Basavaraj"));
Console.WriteLine(" "+test.ReverseWords("yM, eman, si. rmI.na. hK,na"));
;




public class test
{

    public static string DeStackAndReturn(Stack<char> word)
    {
        if (word.Count == 0)
        {
            return "";
        }

        char[] letters = word.ToArray();
        word.Clear();
        return  new string(letters);
    }

    public static string ReverseWords(string sentence)
    {
        StringBuilder fullSentence = new StringBuilder();
        Stack<char> word = new Stack<char>();
        foreach (char c in sentence)
        {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            {
                word.Push(c);
            }
            else
            {
                fullSentence.Append(DeStackAndReturn(word));
                fullSentence.Append(c);
            } }

           fullSentence.Append(DeStackAndReturn(word));


        
        return fullSentence.ToString();
    }
}


/*
 Write a function to reverse words in the sentence.
Reverse the words only.
Dots, spaces and commas should remain as it is.
Words will contain aA to zZ characters only and will not contain anything else.
Delimiters are only dots, spaces and commas.
Delimiters themselves are not the constituents of the word.
For example:
Input (String): My, name. is Basavaraj
Output (String): yM, eman. si jaravasaB
Input (String): yM, eman, si. rmI.na. hK,na
Output (String): My, name, is. Imr.an. Kh,an
 */