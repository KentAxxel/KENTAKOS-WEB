import os
import glob

directory = 'd:/KENTAKOS-WEB/frontend/src'

for filepath in glob.glob(directory + '/**/*.tsx', recursive=True) + glob.glob(directory + '/**/*.ts', recursive=True):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    
    new_lines = []
    changed = False
    for line in lines:
        if 'https://shop.spring.informaticapp.com' in line:
            # Create commented localhost version
            commented_line = line.replace('https://shop.spring.informaticapp.com', 'http://localhost:2920')
            leading_spaces = len(line) - len(line.lstrip())
            commented_line = (' ' * leading_spaces) + '// ' + commented_line.lstrip()
            
            new_lines.append(commented_line)
            new_lines.append(line)
            changed = True
        else:
            new_lines.append(line)
            
    if changed:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        print(f"Updated {filepath}")
