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
            # Línea de la nube comentada
            leading_spaces = len(line) - len(line.lstrip())
            cloud_commented = (' ' * leading_spaces) + '// ' + line.lstrip()
            
            # Línea local activa
            local_active = line.replace('https://shop.spring.informaticapp.com', 'http://localhost:2920')
            
            new_lines.append(cloud_commented)
            new_lines.append(local_active)
            changed = True
        else:
            new_lines.append(line)
            
    if changed:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(new_lines)
        print(f"Updated {filepath}")
