import os
import glob
import re

directory = 'd:/KENTAKOS-WEB/frontend/src'

for filepath in glob.glob(directory + '/**/*.tsx', recursive=True) + glob.glob(directory + '/**/*.ts', recursive=True):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    
    new_lines = []
    changed = False
    skip_next = False
    
    for i in range(len(lines)):
        if skip_next:
            skip_next = False
            continue
            
        line = lines[i]
        
        # If we see a commented localhost line
        if '// ' in line and 'http://localhost:2920' in line:
            # Uncomment it
            uncommented = line.replace('// ', '', 1)
            new_lines.append(uncommented)
            changed = True
            
            # Skip the next line which is the https://shop.spring... line
            if i + 1 < len(lines) and 'https://shop.spring.informaticapp.com' in lines[i+1]:
                skip_next = True
        else:
            new_lines.append(line)
            
    if changed:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        print(f"Reverted {filepath}")
