#!/usr/bin/python

import urllib2
from sets import Set

# using www.mayoclinic.org
# first going for diseases and conditions 
# listed by letter


# list of all the links in a page we're concerned about
links = ['definition', 'symptoms', 'causes', 'risk-factors', 'treatment', 'lifestyle-home-remedies', 'alternative-medicine', 'prevention']

# getting all the letters in a list
# ASCII code for character A-Z = 60 through 90
letters = []
for l in range(65,91):   
    letters.append(str(chr(l)))
    



# create a dictionary of 'disease-condition name' : con-# 
# con-# is used in the url to represent the condition number  
dict_name = {}

def update_dict(page_source):
    for line in page_source:
        stripped_line = line.strip()
        if (stripped_line.startswith('<a href="/diseases-conditions')):
            slash_separated = stripped_line.split('/')
            # [2] is the name [5] is the con-# which requires some cleaning
            name = slash_separated[2]
            con_number = slash_separated[5].split('"')[0]
            dict_name [name] = con_number
 
 
#build url and loop through all the letters

base_url = 'http://www.mayoclinic.org/diseases-conditions/index?letter='

for l in letters:
    url = base_url + l
    response = urllib2.urlopen(url)
    page_source = response.readlines()
    update_dict(page_source)
 
#debugging: =>        
#for x in sorted(dict_name, key=dict_name.get)
#    print x

# now for every con-# in dict_name explore for every option in the links []
def explore(name, con_number):
    present_elements = Set()
    base_url = 'http://www.mayoclinic.org/diseases-conditions/' + name + '/basics/' + con_number
    response = urllib2.urlopen(base_url)
    page_source = response.readlines()
    for link in links:
        for line in page_source:
            temp_str = '/diseases-conditions/' + name + '/basics/' + link + '/' + con_number
            if temp_str in line:
                #print line
                present_elements.add(link)
    return present_elements
                
#ans = explore('acne', 'con-20020580')
#print ans




    
def get_main_content(url):
    #find where the main content of the page is and return that
    #url = 'http://www.mayoclinic.org/diseases-conditions/acne/basics/prevention/con-20020580'
    response = urllib2.urlopen(url);
    page_source = response.read()
    x = page_source.split('<div class="auto-mobile">')
    y = x[0].split('<div class="auto">')
    z = y[1]
    ans = z.split('</div>')[1]
    return ans
    

    
# for every element in dict_name we explore, gather data for present_elements and write to file
f = open('output.txt', 'w')
for name in dict_name:
    f.write(name.upper() + '\r\n')
    print name.upper()
    links = explore(name, dict_name[name])
    for link in links:
        f.write(link + '\r\n')
        print link.upper()
        url = 'http://www.mayoclinic.org/diseases-conditions/' + name + '/basics/' + link + '/' + dict_name[name]
        content = get_main_content(url)
        print content
        f.write(content + '\r\n')
f.close()

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    