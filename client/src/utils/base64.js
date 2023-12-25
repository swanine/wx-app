export function convertImageToBase64(image) {
  const canvas = document.createElement('canvas')
  const context = canvas.getContext('2d')
  canvas.width = image.width
  canvas.height = image.height
  context.drawImage(image, 0, 0)
  return canvas.toDataURL('image/jpeg') // 根据需要指定输出格式
}
// 替换为图片的URL或者base64字符串
