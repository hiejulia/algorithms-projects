from datetime import datetime
from bst import BST, Node 


def get_job_input():
    start_time = input("Enter the time in hh:mm format")
    while True: 
        try: 
            datetime.strftime(start_time, '%H:%M')
        except ValueError:
            print("Error")
            start_time = input("Enter in correct format")
        else:
            break 
    
    duration_job = input("Enter duration")

    while True:
        try:
            int(duration_job)
        except ValueError:
            print("Error")
            duration_job = input("Input again")
        else:
            break

    job_name = input("Enter the name of the job")
    return start_time, duration_job, job_name


my_tree = BST()

with open("data.txt") as f:
    for line in f:
        my_tree.insert(line)


# ....