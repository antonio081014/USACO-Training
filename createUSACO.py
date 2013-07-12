import sys
from datetime import datetime

now = datetime.now();

fileName  = sys.argv[1];
print fileName;

f = open('./' + fileName + '.java', 'w');
f.write('/*\n');
f.write(' ID: antonio13\n');
f.write(' LANG: JAVA' + '\n');
f.write(' PROG: ' + fileName + '\n');
f.write(' */\n');
f.write('import java.io.BufferedReader;\n');
f.write('import java.io.BufferedWriter;\n');
f.write('import java.io.FileReader;\n');
f.write('import java.io.FileWriter;\n');
f.write('/**\n');
f.write(' * @author antonio081014\n');
f.write(' * @time: {0}-{1}-{2}, {3}:{4}:{5}\n'.format(now.year, now.month, now.day, now.hour, now.minute, now.second));
f.write(' */\n');
f.write('\n');
f.write('public class ' + fileName + ' {\n');
f.write('\tpublic static void main(String[] args) throws Exception {\n\t\t'+ fileName +' main = new ' + fileName + '();\n\t\tmain.run();\n\t\tSystem.exit(0);\n\t}\n');
f.write('\n');
f.write('\tpublic void run() throws Exception {\n');
f.write('\t\tBufferedReader br = new BufferedReader(new FileReader(\"' + fileName + '.in\"));\n');
f.write('\t\tBufferedWriter out = new BufferedWriter(new FileWriter(\"' + fileName + '.out\"));\n');
f.write('\t\tout.close();\n');
f.write('\t}\n');
f.write('}\n');
f.write('\n');
f.write('\n');

f.close();

