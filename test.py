def make_box_zigzag(n, w):
    arr = []
    num = 1
    increasing = True  # True: 왼→오, False: 오→왼

    # 총 층 수 = n을 w로 나눈 올림값
    rows = (n + w - 1) // w

    for _ in range(rows):
        row = []  # 한 층

        # w개의 칸을 채우되, n을 넘지 않으면 번호 채움
        for _ in range(w):
            if num <= n:
                row.append(num)
                num += 1
            else:
                row.append(None)  # 남는 공간은 None 처리 (필요하면 제거 가능)

        # 지그재그 방향 처리
        if not increasing:
            row.reverse()

        arr.append(row)
        increasing = not increasing  # 다음 층 방향 반전
    
    return arr


# 테스트
result = make_box_zigzag(13, 3)
for line in result:
    print(line)