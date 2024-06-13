## JRandom

`JRandom` 是我摸鱼时测试 `Java` 随机数编写的项目

它会尝试使用 `CPU` 的 `RDRAND` 生成随机数，如果不支持将会使用 `SecureRandom`

核心代码

```asm
get_rand_int:
    test rdi, rdi
    je fail
    rdrand eax
    jc success
fail:
    xor eax, eax
    ret
success:
    mov [rdi], eax
    mov eax, 1
    ret
```

在 `Java` 层使用会很简单

```java
JRandom random = new JRandom();
for (int i = 0; i < 10; i++) {
    System.out.println(random.randomString(10));
}
```

顺便支持了一些常见的 `API`
- getInt
- getInt(int start, int end) 
- randomString(int length)
- randomBytes(int length)
- randomEle(List<T> list)
- randomNumbers(int n)

目前支持 `Windows` 和 `Linux` 的 `64` 位