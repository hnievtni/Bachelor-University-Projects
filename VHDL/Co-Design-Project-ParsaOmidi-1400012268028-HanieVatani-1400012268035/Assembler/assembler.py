from typing import List, Dict

# Opcode mapping
OPCODES = {
    "load": "00",
    "add": "01",
    "sub": "10",
    "jnz": "11"
}

# Register mapping
REGISTERS = {
    "r0": "00",
    "r1": "01",
    "r2": "10",
    "r3": "11"
}

# Convert a number to binary string (0-63)
def to_binary(num: int, bits: int = 6) -> str:
    return format(max(0, min(num, 63)), f"0{bits}b")

def parse_line(line: str) -> List[str]:
    return line.replace(',', ' ').split()


def compute_label_addresses(lines: List[str]) -> Dict[str, int]:
    addresses = {}
    addr = 0
    for line in lines:
        line = line.strip()
        if not line:
            continue
        if ':' in line:
            label = line.split(':', 1)[0].strip().lower()  # Case-insensitive
            addresses[label] = addr
        opcode = line.split()[0] if ':' not in line else line.split()[1]
        addr += 2 if opcode.lower() == "load" else 1
    return addresses

def assemble(lines: List[str]) -> str:
    label_addresses = compute_label_addresses(lines)
    output = []

    for line in lines:
        line = line.strip()
        if not line:
            continue

        tokens = parse_line(line)
        if not tokens:
            continue

        # Handle label in line
        if ':' in tokens[0]:
            label_part = tokens[0].split(':', 1)
            if label_part[1]:
                tokens[0] = label_part[1]
            else:
                tokens = tokens[1:]
        if not tokens:
            continue

        # Convert opcode and registers to lowercase
        opcode = tokens[0].lower()
        if len(tokens) > 1:
            tokens[1] = tokens[1].lower()
        if len(tokens) > 2:
            tokens[2] = tokens[2].lower()

        # Ensure at least one register
        if len(tokens) < 2:
            raise ValueError(f"Invalid instruction (missing register): {line}")

        reg1 = REGISTERS.get(tokens[1])
        if reg1 is None:
            raise ValueError(f"Unknown register {tokens[1]} in line: {line}")

        if opcode == "load":
            if len(tokens) < 3:
                raise ValueError(f"Load instruction missing value: {line}")
            output.append(OPCODES[opcode] + reg1 + "00")
            output.append(to_binary(int(tokens[2])))
        elif opcode == "jnz":
            if len(tokens) < 3:
                raise ValueError(f"JNZ instruction missing label: {line}")
            label = tokens[2].lower()
            if label not in label_addresses:
                raise ValueError(f"Unknown label {tokens[2]} in line: {line}")
            output.append(OPCODES[opcode] + reg1 + "00")
            output.append(to_binary(label_addresses[label]))
        else:
            if len(tokens) < 3:
                raise ValueError(f"{opcode.upper()} instruction missing second register: {line}")
            reg2 = REGISTERS.get(tokens[2])
            if reg2 is None:
                raise ValueError(f"Unknown register {tokens[2]} in line: {line}")
            output.append(OPCODES[opcode] + reg1 + reg2)

    # End instruction
    output.append("111111")
    return "\n".join(output)

if __name__ == "__main__":
    print("Enter assembly code (empty line to finish):")
    lines = []
    while True:
        line = input()
        if not line.strip():
            break
        lines.append(line)

    try:
        binary_code = assemble(lines)
        print("\nOutput binary code:")
        print(binary_code)
    except ValueError as e:
        print(f"Error: {e}")
