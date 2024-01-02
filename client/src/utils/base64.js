/**
 * 本地文件路径通过读取文件获取文件base64
 * @param filePath
 * @param size 文件大小，如 1024，非必填
 * @returns
 */
import Taro from '@tarojs/taro'

export const getBase64ByFilePath = async (filePath, size) => {
  // 进行压缩， size 仅用于压缩
  // const _filePath = await compressFile({ path, size })
  const _filePath = filePath
  
  return new Promise((resolve, reject) => {
    const fileSystemManager = Taro.getFileSystemManager() // 获取飞书文件管理，通过 readFile 读取文件，微信小程序同理 wx?.getFileSystemManager()
    if (!fileSystemManager) reject({ base64Url: '', success: false })
    const fileType = _filePath?.split('.')?.pop() // 主要是为了拼接 base64 Url，通过飞书文件路径，获取文件类型
    const fileName = _filePath?.split('-')?.pop() // 主要是为了拼接 base64 Url，通过飞书文件路径，获取文件名称（模糊的名称，不算重要）
    fileSystemManager?.readFile({
      filePath: _filePath,
      encoding: 'base64',
      success(res) {
        const base64Url = `data:image/${fileType || 'jpeg'};base64,${res?.data}`
        const result = { base64Url, fileName, fileType, success: true }
        resolve(result)
      },
      fail(res) {
        console.log(`readFile fail: ${JSON.stringify(res)}`)
        const result = { base64Url: '', fileName, fileType, success: false }
        reject(result)
      }
    })
  })
}
